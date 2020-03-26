package com.cedrus.justin_taylor.kafkademo.Services;

//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
		import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

		import java.util.Properties;

@SpringBootApplication
public class PingService {

	public static void main(String[] args) {

		Properties pingProducerProps = new Properties();

		pingProducerProps.put("bootstrap.servers", "localhost:9092,localhost:9093");
		pingProducerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		pingProducerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		pingProducerProps.put("group.id", "test");
		pingProducerProps.put("auto.offset.reset", "earliest");

		KafkaProducer<String, String> pingProducer = new KafkaProducer<String, String>(pingProducerProps);

		try {
			pingProducer.send(new ProducerRecord<String, String>("ping-topic", "pings", "ping"));
		} catch (Exception ex) {

		}
//		KafkaConsumer<String, String> myConsumer = new KafkaConsumer(props);
//		ProducerRecord myRecord = new ProducerRecord("my_topic", "Demo-1", "Hello Kafka World!");

//		ArrayList<String> topics = new ArrayList<>();
//		topics.add("my_topic");

//		myConsumer.subscribe(topics);
//		Duration pollInterval = Duration.ofMillis(100);

//		try {
//			while(true) {
//
//				ConsumerRecords<String, String> records = myConsumer.poll(pollInterval);
//				for(ConsumerRecord<String, String> record : records) {
//					System.out.println("IN MAIN BLOCK");
//					System.out.println("Message: " + record.value());
//				}
//			}
//		} catch (Exception ex) {
//			System.out.println("IN CATCH BLOCK");
//			ex.printStackTrace();
//		} finally {
//			myConsumer.close();
//		}

		SpringApplication.run(PingService.class, args);
	}

}
