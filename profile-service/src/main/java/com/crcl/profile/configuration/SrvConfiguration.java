package com.crcl.profile.configuration;

import com.crcl.common.helper.LocalDateTimeSerializer;
import com.crcl.common.queue.SortDeserializer;
import com.crcl.profile.configuration.security.Oauth2TokenInterceptorHelper;
import com.crcl.profile.configuration.security.ServerRequestInterceptor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;


public class SrvConfiguration {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(Oauth2TokenInterceptorHelper helper) {
        return new ServerRequestInterceptor(helper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Sort.class, new SortDeserializer());
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

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
