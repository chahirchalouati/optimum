package com.crcl.notification.configuration.Oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.resourceserver.jwt")
@Data
public class OAuth2ClientProperties {
    private String issuerUri;
}
