//package com.cedrus.justin_taylor.kafkademo.Services.PingServices;
//import com.cedrus.justin_taylor.kafkademo.Services.ConsumerService;
//import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class PingService {
//    @Autowired
//    private ConsumerService pingConsumer;
//    @Autowired
//    private ProducerService pingProducer;
//
//    public PingService(ConsumerService pingConsumer, ProducerService pingProducer) {
//        this.pingConsumer = pingConsumer;
//        this.pingProducer = pingProducer;
//    }
//
//    public void listenForPongs() {
//        log.info("Listening for Pongs! Ping Service Started!");
//        pingConsumer.start("ping", this.pingProducer);
//    }
//
//    public void sendPing() {
//        pingProducer.sendMessage("ping");
//    }
//}
