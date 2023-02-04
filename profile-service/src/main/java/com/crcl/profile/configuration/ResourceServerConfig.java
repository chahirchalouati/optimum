package com.crcl.profile.configuration;

import com.crcl.common.utils.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@AllArgsConstructor
public class ResourceServerConfig {
    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        http.authorizeHttpRequests(authorize -> authorize
                        .antMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                        .antMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
}
