package com.crcl.storage.configuration.Oauth2;

import com.crcl.common.configuration.SwaggerConfiguration;
import com.crcl.common.properties.ApiProperties;
import com.crcl.common.utils.EndpointsUtils;
import com.crcl.storage.utils.ServerSecurityToken;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static java.util.Objects.nonNull;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
@Import({ApiProperties.class, SwaggerConfiguration.class})
public class ResourceServerConfig {
    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        corsCustomizer.corsCustomizer(http);
        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                .pathMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();


        // TODO: 21/02/2023 find a better solution
        http.addFilterBefore((exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            if (nonNull(headers.get(HttpHeaders.AUTHORIZATION))) {
                ServerSecurityToken.setToken(headers.get(HttpHeaders.AUTHORIZATION).get(0));
            }
            return chain.filter(exchange);
        }, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }
}
