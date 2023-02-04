package com.crcl.comment.configuration.auditing;

import com.crcl.comment.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorAwareConfiguration {
    @Bean
    public AuditorAware<String> auditorAware(UserService userService) {
        return new AuditorAwareImpl(userService);
    }
}
