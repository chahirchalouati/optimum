package com.crcl.am.configuration.clients;

import com.crcl.am.configuration.clients.interceptors.Oauth2TokenInterceptorHelper;
import com.crcl.am.configuration.clients.interceptors.UserOauth2TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthFeignConfig {
    @Bean
    public UserOauth2TokenInterceptor userOauth2TokenInterceptor(Oauth2TokenInterceptorHelper oauth2TokenInterceptorHelper) {
        return new UserOauth2TokenInterceptor(oauth2TokenInterceptorHelper);
    }

}
