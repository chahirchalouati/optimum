package com.crcl.notification.configuration.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


public class Oauth2TokenInterceptor implements ReactiveHttpRequestInterceptor {
    private static final String BEARER_TOKEN_TYPE = "Bearer ";

    @Override
    public Mono<ReactiveHttpRequest> apply(ReactiveHttpRequest reactiveHttpRequest) {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        reactiveHttpRequest.headers().put(AUTHORIZATION, List.of(BEARER_TOKEN_TYPE + authentication.getToken().getTokenValue()));
        return Mono.just(reactiveHttpRequest);
    }
}
