package com.crcl.friend_ship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@ConfigurationPropertiesScan("com.crcl.friend_ship.configuration.*")
@SpringBootApplication
@EnableReactiveFeignClients(basePackages = "com.crcl.processor.clients")
@EnableAspectJAutoProxy
public class ProcessorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcessorServiceApplication.class, args);
    }

}
