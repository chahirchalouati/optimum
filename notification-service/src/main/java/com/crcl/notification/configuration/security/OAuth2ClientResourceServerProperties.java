package com.crcl.notification.configuration.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.resourceserver.jwt")
@Data
public class OAuth2ClientResourceServerProperties {
    private String issuerUri;
}
