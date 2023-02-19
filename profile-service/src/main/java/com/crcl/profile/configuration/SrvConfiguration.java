package com.crcl.profile.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;


public class SrvConfiguration {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(ClientRegistrationRepository registrationRepository,
                                                            OAuth2ClientProperties oAuth2ClientProperties) {
        return new ServerRequestInterceptor(registrationRepository, oAuth2ClientProperties);
    }


}
