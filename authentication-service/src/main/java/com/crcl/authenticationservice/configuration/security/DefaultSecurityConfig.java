package com.crcl.authenticationservice.configuration.security;

import com.crcl.authenticationservice.configuration.web.CorsCustomizer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class DefaultSecurityConfig {

    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        this.corsCustomizer.corsCustomizer(http);
        return http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .anyRequest().authenticated()
                .and().build();
    }
}
