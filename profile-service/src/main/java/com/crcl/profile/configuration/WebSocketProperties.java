package com.crcl.profile.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("web-socket")
public class WebSocketProperties {
    private String endPoint;
    private String[] prefixes;
    private String relayHost;
    private String stompBrokerRelay;
    private int relayPort;
    private String clientLogin;
    private String clientPasscode;
}
