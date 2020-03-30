//package com.cedrus.justin_taylor.kafkademo.Services;
//
//import com.cedrus.justin_taylor.kafkademo.Services.PingServices.PingService;
//import com.cedrus.justin_taylor.kafkademo.Services.PongServices.PongService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class PingPongGame {
//
//    @Autowired
//    private PingService pingService;
//    @Autowired
//    private PongService pongService;
//
//    public PingPongGame(PingService pingService, PongService pongService) {
//        this.pingService = pingService;
//        this.pongService = pongService;
//    }
//
//    public void start() {
//        pongService.listenForPings();
//        pingService.listenForPongs();
//    }
//
//}
