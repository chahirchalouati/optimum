package com.crcl.storage.service.impl;

import com.crcl.storage.configuration.filters.JwtFilterInterceptor;
import com.crcl.storage.configuration.properties.BucketProperties;
import com.crcl.storage.configuration.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class BucketsResolverImpl implements BucketsResolver {
    public static final String DEFAULT_BUCKET_NAME = "default";
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    private final JwtFilterInterceptor jwtFilterInterceptor;

    @Override
    public String resolve() {
        return jwtFilterInterceptor.getJwt().map(jwt -> {
            try {
                final var bucketName = Objects.nonNull(jwt.getClaimAsString("username")) ? jwt.getClaimAsString("username") : jwt.getClaimAsString("sub");
                createBucketsInNotExists(bucketName);
                return bucketName;
            } catch (Throwable e) {
                e.printStackTrace();
                return minioProperties.getBuckets().stream()
                        .filter(BucketProperties::isDefault)
                        .findFirst()
                        .map(BucketProperties::getName)
                        .map(this::createBucketsInNotExists)
                        .orElse(DEFAULT_BUCKET_NAME);
            }
        }).orElse(createBucketsInNotExists(DEFAULT_BUCKET_NAME));

    }

    @SneakyThrows
    private String createBucketsInNotExists(String bucket) {
        BucketExistsArgs existsArgs = BucketExistsArgs.builder().bucket(bucket).build();
        if (!minioClient.bucketExists(existsArgs)) {
            final var makeBucketArgs = MakeBucketArgs.builder().bucket(bucket).build();
            minioClient.makeBucket(makeBucketArgs);
        }
        return bucket;
    }
}
