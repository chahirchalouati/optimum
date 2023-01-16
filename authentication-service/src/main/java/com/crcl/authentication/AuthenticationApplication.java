package com.crcl.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan("com.crcl.authentication.configuration.*")
@EnableMongoAuditing
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
