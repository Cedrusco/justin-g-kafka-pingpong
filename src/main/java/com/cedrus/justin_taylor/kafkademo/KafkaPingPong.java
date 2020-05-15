package com.cedrus.justin_taylor.kafkademo;

import com.cedrus.justin_taylor.kafkademo.Services.PingService;
import com.cedrus.justin_taylor.kafkademo.Services.PongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class KafkaPingPong {

    public static void main(String[] args) {

        SpringApplication.run(KafkaPingPong.class, args);
    }

    @Bean
    public CommandLineRunner pingServiceRunner(ApplicationContext ctx) {
        return args -> ((PingService) ctx.getBean("pingService")).start();
    }

    @Bean
    public CommandLineRunner pongServiceRunner(ApplicationContext ctx) {
        return args -> ((PongService) ctx.getBean("pongService")).start();
    }
}

