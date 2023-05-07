package com.crcl.common.configuration;

import com.crcl.common.properties.ApiProperties;
import com.crcl.common.utils.DefaultScopes;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SwaggerConfiguration {

    @Bean
    public OpenAPI api(ApiProperties apiProperties) {
        log.info("Initializing OpenAPI information {}", apiProperties);

        License license = new License()
                .name(apiProperties.getLicenseName())
                .url(apiProperties.getLicenseUrl());

        Contact contact = new Contact()
                .name(apiProperties.getContactName())
                .email(apiProperties.getContactEmail());

        Info info = new Info()
                .title(apiProperties.getTitle())
                .description(apiProperties.getDescription())
                .version(apiProperties.getVersion())
                .termsOfService(apiProperties.getTermsOfService())
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    @Bean
    @ConditionalOnProperty({
            "api.authorizationUrl",
            "api.tokenUrl"
    })
    public SecurityScheme securityScheme(ApiProperties apiProperties) {
        var securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.OAUTH2);
        var flows = new OAuthFlows();
        var scopes = new Scopes();
        scopes.addString(DefaultScopes.OPENID, DefaultScopes.OPENID);

        var clientCredentials = new OAuthFlow();
        clientCredentials.setAuthorizationUrl(apiProperties.getAuthorizationUrl());
        clientCredentials.setTokenUrl(apiProperties.getTokenUrl());
        clientCredentials.setScopes(scopes);

        flows.setClientCredentials(clientCredentials);
        securityScheme.setFlows(flows);
        securityScheme.setName("OAuth2");
        log.info("Initializing security schema to swagger api/ui {} {}", clientCredentials, apiProperties);

        return securityScheme;
    }
}
