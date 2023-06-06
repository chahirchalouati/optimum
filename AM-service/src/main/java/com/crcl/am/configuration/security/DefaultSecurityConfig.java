package com.crcl.am.configuration.security;

import com.crcl.am.configuration.props.SecurityProperties;
import com.crcl.am.configuration.web.CorsCustomizer;
import com.crcl.common.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class DefaultSecurityConfig {
    private final CorsCustomizer corsCustomizer;
    private final SecurityProperties securityProperties;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        restTemplate.getMessageConverters().add(formHttpMessageConverter);
        return restTemplate;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        this.corsCustomizer.corsCustomizer(http);
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        http.authorizeHttpRequests(
                        registry -> registry.requestMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                                .requestMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                                .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                                .requestMatchers("/authentication/login/**").permitAll()
                                .requestMatchers("/authentication/register/**").permitAll()
                                .requestMatchers("/authentication/roles/**", "/authentication/permissions/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated()
                ).csrf(AbstractHttpConfigurer::disable)
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage(securityProperties.getLoginPage())
                        .failureForwardUrl(securityProperties.getFailureForwardUrl()))
                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()))
                .apply(authorizationServerConfigurer);

        return http.build();
    }
}

