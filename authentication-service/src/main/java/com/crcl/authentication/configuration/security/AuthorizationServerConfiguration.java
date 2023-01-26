package com.crcl.authentication.configuration.security;

import com.crcl.authentication.clients.SrvProfileClient;
import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.configuration.web.CorsCustomizer;
import com.crcl.authentication.mappers.ClientMapper;
import com.crcl.authentication.repository.MongoClientRepository;
import com.crcl.authentication.repository.MongoRegisteredClientRepository;
import com.crcl.authentication.service.impl.ClientSettingsEnhancer;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class AuthorizationServerConfiguration {
    private final CorsCustomizer corsCustomizer;
    private final SecurityProperties securityProperties;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityASFilterChain(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        this.corsCustomizer.corsCustomizer(httpSecurity);
        return httpSecurity.formLogin().loginPage(securityProperties.getLoginPage()).failureForwardUrl(securityProperties.getFailureForwardUrl()).and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(final MongoClientRepository mongoClientRepository, final ClientMapper clientMapper, final ClientSettingsEnhancer clientSettingsEnhancer) {
        return new MongoRegisteredClientRepository(mongoClientRepository, clientMapper, clientSettingsEnhancer);
    }

    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder().issuer(securityProperties.getIssuer()).build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(SrvProfileClient profileClient) {
        return new TokenCustomizer(profileClient);
    }

//    @Bean
//    public OAuth2AuthorizationService authorizationService(MongoOAuth2AuthorizationRepository mongoOAuth2AuthorizationRepository,
//                                                           RegisteredClientRepository registeredClientRepository) {
//        return new MongoOAuth2AuthorizationService(registeredClientRepository, mongoOAuth2AuthorizationRepository);
//    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

}
