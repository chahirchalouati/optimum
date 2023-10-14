package com.crcl.friend_ship.service.impl;

import com.crcl.friend_ship.configuration.filters.JwtFilterInterceptor;
import com.crcl.friend_ship.configuration.properties.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BucketsResolverImplTest extends MinioTestConfiguration {
    @Autowired
    protected MinioClient minioClient;
    @Autowired
    protected MinioProperties minioProperties;
    @Autowired
    protected JwtFilterInterceptor jwtFilterInterceptor;
    @Autowired
    protected BucketsResolverImpl bucketsResolver;


    @Test
    void messageTest() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> buckets = minioClient.listBuckets();
        Assertions.assertNotNull(buckets);

    }

    @Test
    void resolve() {
        String resolve = bucketsResolver.resolve();

        Assertions.assertNotNull(resolve);
    }
}
