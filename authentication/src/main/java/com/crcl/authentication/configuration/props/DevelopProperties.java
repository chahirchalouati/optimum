package com.crcl.authentication.configuration.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dummy")
@Data
public class DevelopProperties {
    private int count;
    private String username;
    private String password;
}

