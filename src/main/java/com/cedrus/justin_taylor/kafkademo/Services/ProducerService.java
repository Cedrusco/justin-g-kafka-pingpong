package com.cedrus.justin_taylor.kafkademo.Services;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Component
@Service
public class ProducerService {
	private Properties producerProps = new Properties();
	private KafkaProducer<String, String> producer;

	@Autowired
	public ProducerService() {
		producerProps.put("bootstrap.servers", "localhost:9092,localhost:9093");
		producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		this.producer = new KafkaProducer<String, String>(producerProps);
	}

	public void sendMessage(String topic, String message) {
		try {
			producer.send(new ProducerRecord<String, String>(topic, message));
		} catch (Exception ex) {
			log.info(ex.getMessage());
			producer.close();
		}
	}

}
