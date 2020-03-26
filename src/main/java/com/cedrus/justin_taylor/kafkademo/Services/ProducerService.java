package com.cedrus.justin_taylor.kafkademo.Services;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static com.cedrus.justin_taylor.kafkademo.Services.ConsumerService.isPingOrPong;

public class ProducerService {
	static Properties producerProps = new Properties();
	static String pingOrPong;
	static KafkaProducer<String, String> producer;

	ProducerService() {}

	public ProducerService(String isPingOrPong) {
		pingOrPong = isPingOrPong;
		producerProps.put("bootstrap.servers", "localhost:9092,localhost:9093");
		producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<String, String>(producerProps);
	}

	public void sendMessage() {
		String message = pingOrPong.replace("p", "P");
		try {
			producer.send(new ProducerRecord<String, String>(pingOrPong, pingOrPong, message + "!"));
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			producer.close();
		}
	};

}
