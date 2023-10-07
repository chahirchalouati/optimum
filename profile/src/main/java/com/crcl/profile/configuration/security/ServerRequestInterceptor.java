package com.crcl.profile.configuration.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;


@RequiredArgsConstructor
public class ServerRequestInterceptor implements RequestInterceptor {
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String BEARER_TOKEN_TYPE = "Bearer";

    private final Oauth2TokenInterceptorHelper helper;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var token = helper.getAccessTokenForCurrentClient();
        if (!StringUtils.isBlank(token)) {
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
        }

    }
}
