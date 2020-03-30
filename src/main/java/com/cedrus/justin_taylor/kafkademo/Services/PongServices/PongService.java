//package com.cedrus.justin_taylor.kafkademo.Services.PongServices;
//import com.cedrus.justin_taylor.kafkademo.Services.ConsumerService;
//import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class PongService {
//    @Autowired
//    private ConsumerService pongConsumer;
//    @Autowired
//    private ProducerService pongProducer;
//
//    public PongService(ConsumerService pongConsumer, ProducerService pongProducer) {
//        this.pongConsumer = pongConsumer;
//        this.pongProducer = pongProducer;
//    }
//
//    public void listenForPings() {
//        log.info("Listening for Pings! Pong Service Started!");
//        pongConsumer.start("pong", this.pongProducer);
//    }
//
//    public void sendPong() {
//        pongProducer.sendMessage("pong");
//    }
//}
