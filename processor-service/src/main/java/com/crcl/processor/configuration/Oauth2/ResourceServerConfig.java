package com.crcl.processor.configuration.Oauth2;

import com.crcl.common.configuration.SwaggerConfiguration;
import com.crcl.common.properties.ApiProperties;
import com.crcl.common.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactivefeign.ReactiveOptions;
import reactivefeign.webclient.WebReactiveOptions;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
@Import({ApiProperties.class, SwaggerConfiguration.class})
public class ResourceServerConfig {
    private final CorsCustomizer corsCustomizer;

    private final OAuth2ClientProperties oAuth2ClientProperties;

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
        return http.build();
    }

    @Bean
    public ReactiveOptions reactiveOptions() {
        return new WebReactiveOptions.Builder()
                .setReadTimeoutMillis(10000)
                .setWriteTimeoutMillis(10000)
                .setResponseTimeoutMillis(10000)
                .build();
    }


    @Bean
    public JwtDecoder decoder(OAuth2ClientProperties oAuth2ClientProperties) {
        return JwtDecoders.fromIssuerLocation(oAuth2ClientProperties.getIssuerUri());
    }
}
