package com.crcl.notification.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "endpoints")
public class SystemEndpointsProps {
    private String configurationUrl;
}
