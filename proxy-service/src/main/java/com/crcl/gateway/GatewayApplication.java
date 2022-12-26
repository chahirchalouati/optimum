package com.crcl.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.RequestPath;
import reactor.core.publisher.Mono;

@SpringBootApplication

public class GatewayApplication {
    Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    @Order(-1)
    public GlobalFilter a() {
        return (exchange, chain) -> {
            logger.info("first pre filter");
            RequestPath path = exchange.getRequest().getPath();
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("third post filter");
            }));
        };
    }

}
