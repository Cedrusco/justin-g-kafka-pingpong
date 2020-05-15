package com.cedrus.justin_taylor.kafkademo.Controller;

import com.cedrus.justin_taylor.kafkademo.Config.BallConfig;
import com.cedrus.justin_taylor.kafkademo.Config.TeamConfig;
import com.cedrus.justin_taylor.kafkademo.Config.TopicConfig;
import com.cedrus.justin_taylor.kafkademo.Message.PingPongMessage;
import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PingPongController {

    private ProducerService producerService;
    private BallConfig ballConfig;
    private TopicConfig topicConfig;
    private TeamConfig teamConfig;

    @Autowired
    public PingPongController(ProducerService producerService, BallConfig ballConfig, TopicConfig topicConfig, TeamConfig teamConfig) {
        this.producerService = producerService;
        this.ballConfig = ballConfig;
        this.topicConfig = topicConfig;
        this.teamConfig = teamConfig;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    @ResponseBody
    public String sendPingMessage() {
        PingPongMessage message = new PingPongMessage(teamConfig.getTeamOneName(), teamConfig.getTeamTwoName(), ballConfig.getTeamOnePayload());
        producerService.sendMessage(topicConfig.getGameTopic(), message);
        return "Success!";
    }

    @RequestMapping(value = "/pong", method = RequestMethod.POST)
    @ResponseBody
    public String sendPongMessage() {
        PingPongMessage message = new PingPongMessage(teamConfig.getTeamTwoName(), teamConfig.getTeamOneName(), ballConfig.getTeamTwoPayload());
        producerService.sendMessage(topicConfig.getGameTopic(), message);
        return "Success!";
    }

}