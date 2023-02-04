package com.crcl.storage.configuration.Oauth2;

import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

import java.util.List;

@Component
public class CorsCustomizer {

    public void corsCustomizer(ServerHttpSecurity http) {
        http.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowCredentials(false);
                cc.setAllowedOriginPatterns(List.of("*")); // bad practice
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowedMethods(List.of("*"));
                return cc;
            };
            c.configurationSource(source);
        });
    }
}
