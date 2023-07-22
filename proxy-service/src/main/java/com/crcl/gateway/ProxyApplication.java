package com.crcl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan("com.crcl.gateway.configurations")
@SpringBootApplication
public class ProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
