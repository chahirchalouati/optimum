package com.crcl.authenticationservice.configuration.security;

import com.crcl.authenticationservice.configuration.web.CorsCustomizer;
import com.crcl.authenticationservice.mappers.ClientMapper;
import com.crcl.authenticationservice.repository.MongoClientRepository;
import com.crcl.authenticationservice.repository.MongoRegisteredClientRepository;
import com.crcl.authenticationservice.service.impl.ClientSettingsEnhancer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class AuthorizationServerConfig {
    private final CorsCustomizer corsCustomizer;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityASFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        corsCustomizer.corsCustomizer(http);
        return http.formLogin().and().build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(MongoClientRepository mongoClientRepository,
                                                                 ClientMapper clientMapper,
                                                                 ClientSettingsEnhancer clientSettingsEnhancer) {
        return new MongoRegisteredClientRepository(mongoClientRepository, clientMapper, clientSettingsEnhancer);
    }

    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
                .issuer("http://auth-server:9000")
                .build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return new TokenCustomizer();
    }
}
