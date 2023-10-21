package com.crcl.processor.configuration.minio;

import com.crcl.processor.configuration.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@AllArgsConstructor
@Slf4j
public class BucketsConfiguration {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;


    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        minioProperties.getBuckets().forEach(
                bucketProperties -> {
                    try {
                        var bucketExists = BucketExistsArgs.builder()
                                .bucket(bucketProperties.getName())
                                .build();

                        if (!minioClient.bucketExists(bucketExists)) {
                            var makeBucketArgs = MakeBucketArgs.builder()
                                    .objectLock(bucketProperties.isObjectLock())
                                    .bucket(bucketProperties.getName())
                                    .build();
                            minioClient.makeBucket(makeBucketArgs);
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
        );
    }
}
