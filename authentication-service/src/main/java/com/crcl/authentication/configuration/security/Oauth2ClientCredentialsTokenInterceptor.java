package com.crcl.authentication.configuration.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;

public class Oauth2ClientCredentialsTokenInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
        final var authentication = (OAuth2ClientAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication == null) {
            return;
        }
        String tokenValue = authentication.getName();
        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, tokenValue));
    }
}
