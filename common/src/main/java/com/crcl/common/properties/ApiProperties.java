package com.crcl.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api")
@Data
public class ApiProperties {
    private String title;
    private String description;
    private String version;
    private String termsOfService;
    private String contactName;
    private String contactEmail;
    private String licenseName;
    private String licenseUrl;
    private String authorizationUrl;
    private String tokenUrl;
}
