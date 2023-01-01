package com.crcl.storage.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProps {
    private String accessKey;
    private String secretKey;
    private String url;
}
