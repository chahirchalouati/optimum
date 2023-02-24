package com.crcl.authentication.configuration.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "security", ignoreInvalidFields = true)
public class SecurityProperties {
    private String issuer;
    private String clientIp;
    private String loginPage;
    private String failureForwardUrl;
    private String certificationBucket;
    private Map<String, Registration> registrations = new HashMap<>();
}
