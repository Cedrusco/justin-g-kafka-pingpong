package com.cedrus.justin_taylor.kafkademo.Controller;

import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {
    private ProducerService producer;

    @Autowired
    public PingPongController(ProducerService producer) {
        this.producer = producer;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    @ResponseBody
    public void sendPing() {
        producer.sendMessage("Ping!");
    }

    @RequestMapping(value = "/pong", method = RequestMethod.POST)
    @ResponseBody
    public void sendPong() {
        producer.sendMessage("Pong!");
    }

}