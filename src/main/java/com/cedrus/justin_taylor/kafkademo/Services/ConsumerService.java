package com.cedrus.justin_taylor.kafkademo.Services;

import com.cedrus.justin_taylor.kafkademo.Config.BallConfig;
import com.cedrus.justin_taylor.kafkademo.Config.KafkaConfig;
import com.cedrus.justin_taylor.kafkademo.Config.PlayerConfig;
import com.cedrus.justin_taylor.kafkademo.Config.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

@Slf4j
@Component
@Service
public class ConsumerService {
    private KafkaConfig kafkaConfig;
    private TopicConfig topicConfig;
    private BallConfig ballConfig;
    private PlayerConfig playerConfig;

    @Autowired
    public ConsumerService(KafkaConfig kafkaConfig, TopicConfig topicConfig, BallConfig ballConfig, PlayerConfig playerConfig) {
        this.kafkaConfig = kafkaConfig;
        this.topicConfig = topicConfig;
        this.ballConfig = ballConfig;
        this.playerConfig = playerConfig;
    }

    public final void start(ProducerService producer) {
        Properties consumerConfig = new Properties();
        consumerConfig.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
        consumerConfig.setProperty("key.deserializer", kafkaConfig.getDeserializer());
        consumerConfig.setProperty("value.deserializer", kafkaConfig.getDeserializer());
        consumerConfig.setProperty("auto.offset.reset", kafkaConfig.getOffsetReset());
        consumerConfig.setProperty("group.id", kafkaConfig.getGroupId());

        final KafkaConsumer consumer = new KafkaConsumer(consumerConfig);
        ArrayList<String> topics = new ArrayList<>();
        topics.add(topicConfig.getPlayerOneTopic());
        topics.add(topicConfig.getPlayerTwoTopic());
        Duration pollInterval = Duration.ofMillis(5000);
        consumer.subscribe(topics);
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(pollInterval);
                for (ConsumerRecord<String, String> record : records) {
                    String incomingMessage = record.value();
                    log.info("Received: " + incomingMessage);
                    String outgoingMessage = incomingMessage.contentEquals(ballConfig.getPlayerOnePayload()) ? ballConfig.getPlayerTwoPayload() : ballConfig.getPlayerOnePayload();
                    if (incomingMessage.contentEquals(ballConfig.getPlayerOnePayload())) {
                        log.info(ballConfig.getPlayerTwoLogMessage());
                        producer.sendMessage(topicConfig.getPlayerTwoTopic(), outgoingMessage);
                    } else if (incomingMessage.contentEquals(ballConfig.getPlayerTwoPayload())) {
                        log.info(playerConfig.getPlayerOneName() + " misses the ball!! Game over.");
                    }
                }
            }
        } catch (Exception ex) {
            log.info(ex.getMessage());
        } finally {
            consumer.close();
        }
    }
}
