package com.crcl.comment.configuration.security;

import com.crcl.core.configuration.SwaggerConfiguration;
import com.crcl.core.properties.ApiProperties;
import com.crcl.core.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@AllArgsConstructor
@Import({ApiProperties.class, SwaggerConfiguration.class})
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {
    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                        .requestMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
