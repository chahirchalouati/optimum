package com.crcl.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@ConfigurationPropertiesScan("com.crcl.profile.configuration.*")
@SpringBootApplication
public class ProfileService {

    public static void main(String[] args) {
        SpringApplication.run(ProfileService.class, args);
    }

}
