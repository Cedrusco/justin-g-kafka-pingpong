package com.cedrus.justin_taylor.kafkademo.Services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

@Slf4j
@Service
public class ConsumerService {
    private Properties consumerConfig;
    private String isPingOrPong ;
    private String trigger;

    @Autowired
    ConsumerService(Properties consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    public final void start(String pingOrPong) {

        this.isPingOrPong = pingOrPong;
        consumerConfig.put("bootstrap.servers", "localhost:9092,localhost:9093");
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("auto.offset.reset", "earliest");
        consumerConfig.put("group.id", pingOrPong+"Group1");
        trigger = pingOrPong == "ping" ? "Pong!" : "Ping!";

        final KafkaConsumer consumer = new KafkaConsumer(consumerConfig);
        ArrayList<String> topics = new ArrayList<>();
        topics.add(isPingOrPong == "ping" ? "pong" : "ping");
        Duration pollInterval = Duration.ofMillis(1000);
        consumer.subscribe(topics);

        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(pollInterval);
                for(ConsumerRecord<String, String> record : records) {
                        log.info(record.value());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            consumer.close();
        }
    }
}
