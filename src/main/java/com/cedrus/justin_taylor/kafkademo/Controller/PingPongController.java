package com.cedrus.justin_taylor.kafkademo.Controller;

import com.cedrus.justin_taylor.kafkademo.Config.BallConfig;
import com.cedrus.justin_taylor.kafkademo.Config.TopicConfig;
import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PingPongController {

    @Autowired
    private ProducerService producerService;
    private BallConfig ballConfig;
    private TopicConfig topicConfig;

    public PingPongController(ProducerService producerService, BallConfig ballConfig, TopicConfig topicConfig) {
        this.producerService = producerService;
        this.ballConfig = ballConfig;
        this.topicConfig = topicConfig;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    @ResponseBody
    public String sendPingMessage() {
        producerService.sendMessage(topicConfig.getPlayerOneTopic(), ballConfig.getPlayerOnePayload());
        return "Ping!";
    }

    @RequestMapping(value = "/pong", method = RequestMethod.POST)
    @ResponseBody
    public String sendPongMessage() {
        producerService.sendMessage(topicConfig.getPlayerTwoTopic(), ballConfig.getPlayerTwoPayload());
        return "Pong!";
    }

}