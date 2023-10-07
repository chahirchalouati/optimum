package com.crcl.processor.configuration.minio;

import com.crcl.processor.configuration.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    @Bean
    @SneakyThrows
    public MinioClient minioClient(MinioProperties minioProperties) {
        return MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getUrl())
                .build();
    }
}
