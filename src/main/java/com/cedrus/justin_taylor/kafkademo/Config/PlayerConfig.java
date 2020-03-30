package com.cedrus.justin_taylor.kafkademo.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "players")
public class PlayerConfig {
    private String playerOneName;
    private String playerTwoName;
}
