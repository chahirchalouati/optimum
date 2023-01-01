package com.crcl.storage.configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    @Bean
    public MinioClient minioClient(MinioProps minioProps) {
        return MinioClient.builder()
                .credentials(minioProps.getAccessKey(), minioProps.getSecretKey())
                .endpoint(minioProps.getUrl())
                .build();
    }

}