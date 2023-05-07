package com.crcl.userInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients(basePackages = "com.crcl.userInfo.client")
@ConfigurationPropertiesScan("com.crcl.userInfo.*")
@SpringBootApplication
@EnableReactiveMongoRepositories("com.crcl.userInfo.repository")
public class UserInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInfoApplication.class, args);
    }

}
