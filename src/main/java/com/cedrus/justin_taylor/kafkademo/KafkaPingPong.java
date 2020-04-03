package com.cedrus.justin_taylor.kafkademo;

import com.cedrus.justin_taylor.kafkademo.Services.ConsumerService;
import com.cedrus.justin_taylor.kafkademo.Services.PingService;
import com.cedrus.justin_taylor.kafkademo.Services.PongService;
import com.cedrus.justin_taylor.kafkademo.Services.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Bean
//    public CommandLineRunner GameRunner(ApplicationContext ctx) {
//        try {
//            return args -> {
//                ((ConsumerService) ctx.getBean("consumerService")).start(producerService);
//
//            };
//        } catch (RuntimeException rex) {
//            log.error(
//                    FATAL_MARKER,
//                    "RuntimeException encountered when trying to start the service using CommandLineRunner message={}",
//                    rex.getMessage());
//            log.error("RuntimeException", rex);
//            throw rex;
//        }
//    }
}

