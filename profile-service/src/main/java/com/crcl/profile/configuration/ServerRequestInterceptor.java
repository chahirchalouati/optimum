package com.crcl.profile.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@RequiredArgsConstructor
public class ServerRequestInterceptor implements RequestInterceptor {
    private final ClientRegistrationRepository registrationRepository;
    private final OAuth2ClientProperties oAuth2ClientProperties;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println(oAuth2ClientProperties);

    }
}
