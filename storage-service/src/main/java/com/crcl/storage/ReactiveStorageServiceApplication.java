package com.crcl.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan("com.crcl.storage.configuration")
@SpringBootApplication
public class ReactiveStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveStorageServiceApplication.class, args);
    }

}
