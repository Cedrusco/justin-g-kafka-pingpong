package com.cedrus.justin_taylor.kafkademo;

import com.cedrus.justin_taylor.kafkademo.Services.PingServices.PingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaPingPong {
    private PingService pingService;

    public KafkaPingPong(PingService pingService) {
        this.pingService = pingService;
    }

    public static void main(String[] args) {

        SpringApplication.run(KafkaPingPong.class, args);
    }
}
