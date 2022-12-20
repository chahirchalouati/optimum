package com.crcl.proxyservice;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class TokenRelayGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenRelayGatewayFilterFactory.Config> {

    private static final String AUTH_TOKEN_HEADER = "my-auth-token";
    private static final String AUTH_TOKEN_VALUE = "my-secret-token";

    public TokenRelayGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String authorization = headers.getOrEmpty("Authorization").stream().findFirst().orElse(null);
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("Authorization", authorization)
                    .build();

            var builder = exchange.mutate().request(request).build();
            System.out.println(builder.getRequest().getHeaders());

            return chain.filter(builder);
        };
    }

    public static class Config {
        // Put the configuration properties for your filter here
    }

}