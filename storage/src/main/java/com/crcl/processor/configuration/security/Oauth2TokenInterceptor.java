package com.crcl.processor.configuration.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


public class Oauth2TokenInterceptor implements RequestInterceptor {
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) securityContext.getAuthentication();

        if (authentication == null) {
            return;
        }
        String tokenValue = authentication.getToken().getTokenValue();
        template.header(AUTHORIZATION, String.format("%s %s", BEARER_TOKEN_TYPE, tokenValue));
    }
}
