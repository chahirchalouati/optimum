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
            CorsConfigurationSource source = s -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowCredentials(true);
                cc.setAllowedOriginPatterns(List.of("*")); // bad practice
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowedMethods(List.of("*"));
                return cc;
            };

            corsConfigurer.configurationSource(source);
        });
    }
}
