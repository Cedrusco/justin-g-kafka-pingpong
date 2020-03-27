package com.cedrus.justin_taylor.kafkademo.Services;

import com.cedrus.justin_taylor.kafkademo.Services.PingServices.PingService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class ConsumerService {
    static Properties consumerConfig;
    static String isPingOrPong ;
    static String trigger;
    private PingService pingService;

    ConsumerService() {

    }

    public final void start(String pingOrPong) {

        this.isPingOrPong = pingOrPong;
        consumerConfig.put("bootstrap.servers", "localhost:9092,localhost:9093");
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("auto.offset.reset", "earliest");
        consumerConfig.put("group.id", pingOrPong+"Group1");
        trigger = pingOrPong == "ping" ? "Pong!" : "Ping!";

        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerConfig);
        ArrayList<String> topics = new ArrayList<>();
        topics.add(isPingOrPong == "ping" ? "pong" : "ping");
        Duration pollInterval = Duration.ofMillis(1000);
        consumer.subscribe(topics);

        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(pollInterval);
                for(ConsumerRecord<String, String> record : records) {
                    if(record.value().toLowerCase() == trigger) {
                        wait(500);
                        pingService.sendPingMessage();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            consumer.close();
        }
    }
}
