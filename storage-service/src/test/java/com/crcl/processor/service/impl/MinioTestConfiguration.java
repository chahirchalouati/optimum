package com.crcl.processor.service.impl;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.GenericContainer;

import java.util.function.Consumer;

public class MinioTestConfiguration {
    protected static int hostPort = 6380;
    protected static int containerExposedPort = 6379;
    protected static Consumer<CreateContainerCmd> cmd = e -> e.withPortBindings(
            new PortBinding(
                    Ports.Binding.bindPort(hostPort),
                    new ExposedPort(containerExposedPort
                    )));

    protected static GenericContainer container = new GenericContainer("minio/minio")
            .withExposedPorts(47017, 47018)
            .withEnv("MINIO_ROOT_USER", "myaccesskey")
            .withEnv("MINIO_ROOT_PASSWORD", "mysecretkey")
            .withEnv("MINIO_BUCKET", "processor");

//    @DynamicPropertySource
//    public static void setupSource(DynamicPropertyRegistry dynamicPropertyRegistry) {
//        container.start();
//        dynamicPropertyRegistry.add("spring.data.mongodb.database", () -> "test_storage");
//        dynamicPropertyRegistry.add("spring.data.mongodb.host", container::getContainerIpAddress);
//        dynamicPropertyRegistry.add("spring.data.mongodb.port", container::getFirstMappedPort);
//        dynamicPropertyRegistry.add("spring.data.mongodb.authentication-database", () -> "admin");
//    }
}
