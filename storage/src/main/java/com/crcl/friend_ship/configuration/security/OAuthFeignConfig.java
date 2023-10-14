package com.crcl.friend_ship.configuration.security;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class OAuthFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new Oauth2TokenInterceptor();
    }

}
