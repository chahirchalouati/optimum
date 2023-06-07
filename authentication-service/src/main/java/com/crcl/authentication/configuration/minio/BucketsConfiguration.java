package com.crcl.authentication.configuration.minio;


import com.crcl.authentication.configuration.props.BucketProperties;
import com.crcl.authentication.configuration.props.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
@Slf4j
public class BucketsConfiguration {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Bean
    public CommandLineRunner handleApplicationReadyEvent() {
        System.out.println(minioProperties);

        return args -> {
            List<BucketProperties> buckets = minioProperties.getBuckets();

            if (buckets.isEmpty()) {
                log.info("The buckets list is empty.");
                return;
            }

            buckets.forEach(this::createBucketIfNotExists);
        };
    }

    private void createBucketIfNotExists(BucketProperties bucketProperties) {
        try {
            if (!bucketExists(bucketProperties.getName())) {
                makeBucket(bucketProperties);
                log.info("{} bucket was created successfully.", bucketProperties.getName());
            }
        } catch (Exception e) {
            log.error("Error creating Minio buckets: {}", e.getMessage());
        }
    }

    private boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error("Error checking if bucket exists: {}", e.getMessage());
            return false;
        }
    }

    private void makeBucket(BucketProperties bucketProperties) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .objectLock(bucketProperties.isObjectLock())
                    .bucket(bucketProperties.getName())
                    .build());
        } catch (Exception e) {
            log.error("Error creating bucket: {}", e.getMessage());
        }
    }
}

