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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
@Component
public class PingService {

    private TopicConfig topicConfig;
    private KafkaConfig kafkaConfig;
    private BallConfig ballConfig;
    private StreamsBuilder streamsBuilder = new StreamsBuilder();

    @Autowired
    public PingService(TopicConfig topicConfig, KafkaConfig kafkaConfig, BallConfig ballConfig) {
        this.topicConfig = topicConfig;
        this.kafkaConfig = kafkaConfig;
        this.ballConfig = ballConfig;
    }


    public void start() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaConfig.getPingApplicationId());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getOffsetReset());

        streamsBuilder.stream(topicConfig.getPlayerTwoTopic())
                .mapValues(val ->
                        String.valueOf(val) == String.valueOf(ballConfig.getPlayerTwoPayload())
                                ? ballConfig.getPlayerOnePayload() : "Won Game!")
                .to((topicConfig.getPlayerOneTopic()));
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);

        streams.start();
    }
}


//.mapValues(
//        val->String.valueOf(val)==ballConfig?ballConfig.getPlayerOnePayload():"Error: Wrong Ball!").to(topicConfig.getPlayerOneTopic());