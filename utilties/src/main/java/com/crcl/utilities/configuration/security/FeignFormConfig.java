package com.crcl.utilities.configuration.security;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@AllArgsConstructor
public class FeignFormConfig {

    @Bean
    public Encoder multipartFormEncoder() {
        List<HttpMessageConverter<?>> converters = new RestTemplate().getMessageConverters();
        HttpMessageConverters httpMessageConverters = new HttpMessageConverters(converters);
        return new SpringFormEncoder(new SpringEncoder(() -> httpMessageConverters));
    }

}
