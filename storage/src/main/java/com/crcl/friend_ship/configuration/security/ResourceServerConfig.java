package com.crcl.friend_ship.configuration.security;

import com.crcl.core.configuration.SwaggerConfiguration;
import com.crcl.core.properties.ApiProperties;
import com.crcl.core.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
@Import({ApiProperties.class, SwaggerConfiguration.class}) // TODO: 16/09/23 move to main class 
public class ResourceServerConfig {
    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        corsCustomizer.corsCustomizer(http);
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec
                                .pathMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                                .pathMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
