package com.cedrus.justin_taylor.kafkademo.Services;

import com.cedrus.justin_taylor.kafkademo.Config.BallConfig;
import com.cedrus.justin_taylor.kafkademo.Config.KafkaConfig;
import com.cedrus.justin_taylor.kafkademo.Config.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.kstream.ValueTransformerSupplier;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
@Component
public class PongService {

    private TopicConfig topicConfig;
    private KafkaConfig kafkaConfig;
    private BallConfig ballConfig;
    private StreamsBuilder streamsBuilder = new StreamsBuilder();

    @Autowired
    public PongService(TopicConfig topicConfig, KafkaConfig kafkaConfig, BallConfig ballConfig) {
        this.topicConfig = topicConfig;
        this.kafkaConfig = kafkaConfig;
        this.ballConfig = ballConfig;
    }


    public void start() {
        Properties props = new Properties();

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaConfig.getPongApplicationId());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getOffsetReset());

        String playerOnePayload = ballConfig.getPlayerOnePayload();

        streamsBuilder.stream(topicConfig.getPlayerOneTopic()).mapValues(
                val -> String.valueOf(val) == playerOnePayload
                        ? ballConfig.getPlayerTwoPayload()
                        : "Did not receive a " + playerOnePayload)
                .transformValues(delayBallReturn())
                .to(topicConfig.getPlayerTwoTopic());
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);

        streams.start();
    }

    private ValueTransformerSupplier delayBallReturn() {
        return () ->
                new ValueTransformer<String, String>() {
                    @Override
                    public void init(ProcessorContext context) {
                        // Necessary for the class, but not needed technically.
                    }

                    @Override
                    public String transform(String val) {
                        final Long timeDelayInMillis = 5000L;
                        log.info("Starting Sleep");
                        try {
                            Thread.sleep(timeDelayInMillis);
                            return val;
                        } catch (InterruptedException ix) {
                            log.info("Thread Interrupted");
                        }
                        return val;
                    }

                    @Override
                    public void close() {
                    }
                };
    }
}

