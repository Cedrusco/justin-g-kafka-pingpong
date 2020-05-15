package com.cedrus.justin_taylor.kafkademo.Message;

import lombok.Data;

@Data
public class PingPongMessage {
    private String sourceTeam;
    private String targetTeam;
    private String message;

    public PingPongMessage(String sourceTeam, String targetTeam, String message) {
        this.sourceTeam = sourceTeam;
        this.targetTeam = targetTeam;
        this.message = message;
    }
}
