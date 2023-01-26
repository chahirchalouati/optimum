package com.crcl.storage.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String accessKey;
    private String secretKey;
    private String url;
    private String region;
    private String baseurl;
    private List<BucketProperties> buckets = new ArrayList<>();
}
