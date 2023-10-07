package com.crcl.authentication.configuration.web;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
public class CorsCustomizer {

    public void corsCustomizer(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(corsConfigurer -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setAllowedOriginPatterns(List.of("*")); // Bad practice
                corsConfiguration.setAllowedHeaders(List.of("*"));
                corsConfiguration.setAllowedMethods(List.of("*"));
                return corsConfiguration;
            };

            corsConfigurer.configurationSource(source);
        });
    }
}
