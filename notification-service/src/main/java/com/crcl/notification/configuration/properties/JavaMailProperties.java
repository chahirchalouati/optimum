package com.crcl.notification.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mail", ignoreUnknownFields = false)
public class JavaMailProperties {
    private String protocol;
    private String host;
    private int port = -1;
    private String username;
    private String password;
    private String defaultEncoding;
    private boolean auth = true;
    private boolean enableTls = true;
    private boolean debug = false;
}







