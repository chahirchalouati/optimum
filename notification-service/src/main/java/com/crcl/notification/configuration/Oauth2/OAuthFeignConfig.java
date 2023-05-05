package com.crcl.notification.configuration.Oauth2;

import org.springframework.context.annotation.Bean;
import reactivefeign.client.ReactiveHttpRequestInterceptor;

public class OAuthFeignConfig {
    @Bean
    public ReactiveHttpRequestInterceptor requestInterceptor() {
        return new Oauth2TokenInterceptor();
    }

}
