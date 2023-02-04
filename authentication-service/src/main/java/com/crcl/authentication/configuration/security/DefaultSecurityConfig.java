package com.crcl.authentication.configuration.security;

import com.crcl.authentication.configuration.web.CorsCustomizer;
import com.crcl.common.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class DefaultSecurityConfig {
    private final CorsCustomizer corsCustomizer;

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
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();
        http.authorizeRequests()
                .antMatchers(EndpointsUtils.Ignorable.SWAGGER_API).permitAll()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers("/idp/login/**").permitAll()
                .antMatchers("/idp/register/**").permitAll()
                .antMatchers("/idp/roles/**", "/idp/permissions/**").hasAnyRole("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin(fm -> fm.loginPage("/idp/login"));
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .apply(authorizationServerConfigurer);

        return http.build();
    }
}
