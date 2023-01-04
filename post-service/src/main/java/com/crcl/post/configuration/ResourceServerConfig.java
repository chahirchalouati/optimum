package com.crcl.post.configuration;

import com.crcl.common.properties.EndpointsUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class ResourceServerConfig {

    private final CorsCustomizer corsCustomizer;

    @Bean
    public EvaluationContextExtension evaluationContextExtension() {
        return new PrincipalResolver();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        corsCustomizer.corsCustomizer(http);
        http.authorizeHttpRequests(authorize -> authorize
                        .antMatchers(EndpointsUtils.Excludable.END_POINT_ACTUATOR).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
}
