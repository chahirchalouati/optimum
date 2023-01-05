package com.crcl.gateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class ServerConfig implements WebFluxConfigurer {

    @Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();

            return chain.filter(exchange);
        };
    }
}
