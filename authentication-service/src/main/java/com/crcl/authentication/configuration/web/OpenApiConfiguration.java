package com.crcl.authentication.configuration.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI api() {
        // TODO add more info about api
        Info info = new Info();
        info.setTitle("Authentication service");
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        return openAPI;
    }
}
