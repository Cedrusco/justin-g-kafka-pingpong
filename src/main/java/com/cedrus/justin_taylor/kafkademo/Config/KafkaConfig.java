package com.cedrus.justin_taylor.kafkademo.Config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kafka.common")
public class KafkaConfig {
    private String bootstrapServers;
    private String deserializer;
    private String serializer;
    private String offsetReset;
    private String groupId;
    private String pingApplicationId;
    private String pongApplicationId;
}