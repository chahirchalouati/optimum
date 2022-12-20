package com.crcl.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PostService {

    public static void main(String[] args) {
        SpringApplication.run(PostService.class, args);
    }
}
