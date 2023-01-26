package com.crcl.storage.configuration.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
@AllArgsConstructor
public class JwtFilterInterceptor implements WebFilter {
    private static final String BEARER = "Bearer ";
    private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
    @Getter
    private Optional<Jwt> jwt;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) return chain.filter(exchange);
        String token = headers.toSingleValueMap().get(HttpHeaders.AUTHORIZATION);
        if (token.contains(BEARER)) {
            token = token.replace(BEARER, EMPTY);
        }
        final var jwtDecoder = JwtDecoders.fromIssuerLocation(oAuth2ResourceServerProperties.getJwt().getIssuerUri());
        jwt = Optional.ofNullable(jwtDecoder.decode(token));
        return chain.filter(exchange);
    }
}
