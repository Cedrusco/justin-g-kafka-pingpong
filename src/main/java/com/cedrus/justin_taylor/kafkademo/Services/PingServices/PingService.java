package com.cedrus.justin_taylor.kafkademo.Services.PingServices;
import com.cedrus.justin_taylor.kafkademo.Services.ConsumerService;
import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingService {
    @Autowired
    private ConsumerService pingConsumer;
    private ProducerService pingProducer;

    public void startConsumer() {
        pingConsumer.start("ping");
    }

    public void sendPingMessage() {
        pingProducer.sendMessage("Ping!");
    }
}
