package com.crcl.core.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DevelopmentConfiguration {
    @Bean
    @Profile("dev")
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
