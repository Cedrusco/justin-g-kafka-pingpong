package com.cedrus.justin_taylor.kafkademo.Controller;

import com.cedrus.justin_taylor.kafkademo.Services.ConsumerService;
import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PingPongController {

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private ProducerService producerService;

    public PingPongController(ConsumerService consumerService, ProducerService producerService) {
        this.consumerService = consumerService;
        this.producerService = producerService;
    }
//
//    @RequestMapping(value = "/start", method = RequestMethod.POST)
//    @ResponseBody
//    public String startConsumerServices() {
//        consumerService.start(producerService);
//        return "Consumers Listening!";
//    }

//    @RequestMapping(value="/start-ping-c")
//    @ResponseBody
//    public String startPingConsumer() {
//
//    }

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    @ResponseBody
    public String sendPingMessage() {
        producerService.sendMessage("ping", "ping");
        return "Ping!";
    }

    @RequestMapping(value = "/pong", method = RequestMethod.POST)
    @ResponseBody
    public String sendPongMessage() {
        producerService.sendMessage("pong", "pong");
        return "Pong!";
    }

}