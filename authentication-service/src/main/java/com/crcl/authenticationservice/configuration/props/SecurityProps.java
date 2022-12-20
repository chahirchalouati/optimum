package com.crcl.authenticationservice.configuration.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "security", ignoreUnknownFields = false)
public class SecurityProps {
    private String issuer;
    private String clientIp;
    private Map<String, ClientsProps> clientsProps = new HashMap<>();
}
