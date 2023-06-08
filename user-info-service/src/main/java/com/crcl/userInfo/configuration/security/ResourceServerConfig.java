package com.crcl.userInfo.configuration.security;

import com.crcl.common.configuration.SwaggerConfiguration;
import com.crcl.common.properties.ApiProperties;
import com.crcl.common.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestTemplate;
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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ReactiveOptions reactiveOptions() {
        return new WebReactiveOptions.Builder()
                .setReadTimeoutMillis(2000)
                .setWriteTimeoutMillis(2000)
                .setResponseTimeoutMillis(2000)
                .build();
    }

    @Bean
    public JwtDecoder decoder(OAuth2ClientResourceServerProperties resourceServerProperties) {
        return JwtDecoders.fromIssuerLocation(resourceServerProperties.getIssuerUri());
    }

}
