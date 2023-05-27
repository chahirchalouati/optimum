package com.crcl.profile.configuration.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("authorization")
public class AuthorizationProperties {
    private String authorizationUrl;
    private String tokenUrl;

}
