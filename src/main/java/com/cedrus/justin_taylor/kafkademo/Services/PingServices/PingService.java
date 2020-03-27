package com.cedrus.justin_taylor.kafkademo.Services.PingServices;
import com.cedrus.justin_taylor.kafkademo.Services.ConsumerService;
import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;

public class PingService {
    private ConsumerService pingConsumer;
    private ProducerService pingProducer;

    PingService() {}

    @Autowired
    public PingService(ConsumerService pingConsumer, ProducerService pingProducer) {
        this.pingConsumer = pingConsumer;
        this.pingProducer = pingProducer;
    }

    public void startConsumer() {
        pingConsumer.start("ping");
    }

    public void sendPingMessage() {
        pingProducer.sendMessage("Ping!");
    }
}
