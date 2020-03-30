package com.cedrus.justin_taylor.kafkademo.Services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

@Slf4j
@Service
public class ConsumerService {
    private Properties consumerConfig;

    public final void start(ProducerService producer) {
        consumerConfig = new Properties();
        consumerConfig.put("bootstrap.servers", "localhost:9092,localhost:9093");
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("auto.offset.reset", "latest");
        consumerConfig.put("group.id", "Group1");

        final KafkaConsumer consumer = new KafkaConsumer(consumerConfig);
        ArrayList<String> topics = new ArrayList<>();
        topics.add("ping");
        topics.add("pong");
        Duration pollInterval = Duration.ofMillis(5000);
        consumer.subscribe(topics);
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(pollInterval);
                for (ConsumerRecord<String, String> record : records) {
                    String incomingMessage = record.value();
                    log.info("Received: " + incomingMessage);
                    String outgoingTopicAndMessage = incomingMessage.contentEquals("ping!") ? "pong" : "ping";
                    log.info("Sending: " + outgoingTopicAndMessage);
                    if (incomingMessage.contentEquals("ping!")) {
                        log.info("Hit Pong! Get Ready!");
                        producer.sendMessage(outgoingTopicAndMessage, outgoingTopicAndMessage);
                    } else if (incomingMessage.contentEquals("pong!")) {
                        log.info("Hit Ping! Get Ready!");
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
