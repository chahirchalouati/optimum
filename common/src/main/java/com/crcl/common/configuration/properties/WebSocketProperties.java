package com.crcl.common.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
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
