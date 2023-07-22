package com.crcl.reaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableFeignClients
@ConfigurationPropertiesScan("com.crcl.reaction.configuration")
@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoAuditing
public class UtilitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilitiesApplication.class, args);
    }
}
