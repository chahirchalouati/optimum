package com.crcl.authentication.configuration.clients;

import com.crcl.authentication.configuration.clients.interceptors.Oauth2TokenInterceptorHelper;
import com.crcl.authentication.configuration.clients.interceptors.ServiceOauth2TokenInterceptorHelper;
import com.crcl.authentication.repository.MongoClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SrvOauth2ClientConfig {
    @Bean
    public ServiceOauth2TokenInterceptorHelper serviceOauth2TokenInterceptorHelper(MongoClientRepository mongoClientRepository, Oauth2TokenInterceptorHelper oauth2TokenInterceptorHelper) {
        return new ServiceOauth2TokenInterceptorHelper(mongoClientRepository, oauth2TokenInterceptorHelper);
    }

}
