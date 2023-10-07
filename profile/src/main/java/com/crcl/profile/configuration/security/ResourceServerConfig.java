package com.crcl.profile.configuration.security;

import com.crcl.core.configuration.SwaggerConfiguration;
import com.crcl.core.helper.LocalDateTimeDeserializer;
import com.crcl.core.helper.LocalDateTimeSerializer;
import com.crcl.core.properties.ApiProperties;
import com.crcl.core.queue.SortDeserializer;
import com.crcl.core.utils.EndpointsUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.time.LocalDateTime;

@EnableWebSecurity
@AllArgsConstructor
@Import({ApiProperties.class, SwaggerConfiguration.class}) // TODO: 16/09/23 move to main class 
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {
    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                        .requestMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Sort.class, new SortDeserializer());
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        return new ObjectMapper()
                .registerModule(module)
                .registerModule(new PageJacksonModule())
                .registerModule(simpleModule)
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    }
}
