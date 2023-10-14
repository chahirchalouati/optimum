package com.crcl.friend_ship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@ConfigurationPropertiesScan("com.crcl.friend_ship.configuration.*")
@SpringBootApplication
@EnableFeignClients
public class FriendShipServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendShipServiceApplication.class, args);
    }

}
