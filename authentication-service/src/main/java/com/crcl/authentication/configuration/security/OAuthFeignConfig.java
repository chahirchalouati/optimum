package com.crcl.authentication.configuration.security;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OAuthFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor(@Value("${client.idp.url}") String idpUrl, RestTemplate restTemplate) {
        return new Oauth2TokenInterceptor(restTemplate, idpUrl);
    }

}
