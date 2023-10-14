package com.crcl.friend_ship.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BaseRepositoryConfiguration {

    static GenericContainer container = new GenericContainer("mongo:6.0.2")
            .withExposedPorts(27017);

    @DynamicPropertySource
    public static void setupSource(DynamicPropertyRegistry dynamicPropertyRegistry) {
        container.start();
        dynamicPropertyRegistry.add("spring.data.mongodb.database", () -> "test_storage");
        dynamicPropertyRegistry.add("spring.data.mongodb.host", container::getContainerIpAddress);
        dynamicPropertyRegistry.add("spring.data.mongodb.port", container::getFirstMappedPort);
        dynamicPropertyRegistry.add("spring.data.mongodb.authentication-database", () -> "admin");
    }
}
