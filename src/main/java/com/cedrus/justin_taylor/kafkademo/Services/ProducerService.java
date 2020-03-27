package com.cedrus.justin_taylor.kafkademo.Services;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

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

	public void sendMessage(String pingOrPong) {
		String message = pingOrPong.replace("p", "P");
		try {
			producer.send(new ProducerRecord<String, String>("pong", pingOrPong, message + "!"));
			System.out.println("Sending: "+pingOrPong);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			producer.close();
		}
	}

}
