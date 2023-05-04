package com.crcl.userInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients(basePackages = "com.crcl.userInfo.client")
@ConfigurationPropertiesScan("com.crcl.userInfo.configuration.*")
@SpringBootApplication
public class UserInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInfoApplication.class, args);
    }

}
