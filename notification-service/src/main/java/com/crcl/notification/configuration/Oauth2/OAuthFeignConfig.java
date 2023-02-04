package com.crcl.notification.configuration.Oauth2;

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
