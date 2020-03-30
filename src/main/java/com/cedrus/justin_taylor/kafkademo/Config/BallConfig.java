package com.cedrus.justin_taylor.kafkademo.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kafka.messages")
public class BallConfig {
    private String playerOneLogMessage;
    private String playerOnePayload;
    private String playerTwoLogMessage;
    private String playerTwoPayload;
}