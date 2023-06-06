package com.crcl.post.configuration.security;

import com.crcl.common.configuration.SwaggerConfiguration;
import com.crcl.common.properties.ApiProperties;
import com.crcl.common.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@AllArgsConstructor
@Import({ApiProperties.class, SwaggerConfiguration.class})
public class ResourceServerConfig {
    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                        .requestMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                        .requestMatchers("/websocket/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
