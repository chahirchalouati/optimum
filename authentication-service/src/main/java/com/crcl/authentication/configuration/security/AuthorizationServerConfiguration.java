package com.crcl.authentication.configuration.security;

import com.crcl.authentication.clients.SrvProfileClient;
import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.configuration.web.CorsCustomizer;
import com.crcl.authentication.mappers.ClientMapper;
import com.crcl.authentication.repository.MongoClientRepository;
import com.crcl.authentication.repository.MongoRegisteredClientRepository;
import com.crcl.authentication.service.impl.ClientSettingsEnhancer;
import com.crcl.common.configuration.SwaggerConfiguration;
import com.crcl.common.properties.ApiProperties;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Import({ApiProperties.class, SwaggerConfiguration.class})
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        this.corsCustomizer.corsCustomizer(httpSecurity);
        return httpSecurity.formLogin()
                .loginPage(securityProperties.getLoginPage())
                .failureForwardUrl(securityProperties.getFailureForwardUrl())
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
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

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    @SneakyThrows
    public JWKSource<SecurityContext> jwkSource(JwkProvider jwkProvider) {
        KeyPair keyPair = jwkProvider.getLastEnabledKeyPair();
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID("authentication-server-key")
                .build();
        return (jwkSelector, securityContext) -> jwkSelector.select(new JWKSet(rsaKey));
    }
}
