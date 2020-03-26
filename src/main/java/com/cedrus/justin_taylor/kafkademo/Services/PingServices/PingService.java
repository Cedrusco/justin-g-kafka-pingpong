package com.cedrus.justin_taylor.kafkademo.Services.PingServices;
import com.cedrus.justin_taylor.kafkademo.Services.ConsumerService;
import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;

public class PingService {
    private ConsumerService pingConsumer;
    private ProducerService pingProducer;

    PingService() {}

    public PingService(ConsumerService pingConsumer, ProducerService pingProducer) {
        this.pingConsumer = pingConsumer;
        this.pingProducer = pingProducer;
    }

    public void startConsumer() {
        pingConsumer.start("ping");
    }

    public void sendPingMessage() {
        pingProducer.sendMessage();
    }
}
