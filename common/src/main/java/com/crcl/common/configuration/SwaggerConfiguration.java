package com.crcl.common.configuration;

import com.crcl.common.properties.ApiProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Autowired
    private ApiProperties apiProperties;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title(apiProperties.getTitle()).description(apiProperties.getDescription())
                        .version(apiProperties.getVersion())
                        .termsOfService(apiProperties.getTermsOfService())
                        .contact(new Contact().name(apiProperties.getContactName()).email(apiProperties.getContactEmail()))
                        .license(new License().name(apiProperties.getLicenseName()).url(apiProperties.getLicenseUrl()))
                );
    }
}
