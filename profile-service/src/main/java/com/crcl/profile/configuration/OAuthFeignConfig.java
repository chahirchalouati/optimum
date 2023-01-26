package com.crcl.profile.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new Oauth2TokenInterceptor();
    }

}
