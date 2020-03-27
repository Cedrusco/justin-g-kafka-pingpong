package com.cedrus.justin_taylor.kafkademo;

import com.cedrus.justin_taylor.kafkademo.Services.PingServices.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaPingPong {

    @Autowired
    private PingService pingService;

    public static void main(String[] args) {

        SpringApplication.run(KafkaPingPong.class, args);
    }
}
