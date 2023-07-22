package com.crcl.gateway.configurations;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.util.CastUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import java.util.logging.Level;

import static com.crcl.common.utils.GramifyConstants.*;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.BEARER;

@Configuration
@RequiredArgsConstructor
public class CustomFilters {

    @Bean
    public WebClient authenticationClient(@Value("${client.authentication.url}") String url) {
        return WebClient.create(url);
    }

    @Bean
    @Profile("dev")
    public GlobalFilter developFilter(WebClient authenticationClient,
                                      OAuth2ClientProperties oAuth2ClientProperties) {
        return (exchange, chain) -> {
            final var headers = exchange.getRequest().getHeaders();
            final var hasDevUser = headers.toSingleValueMap().containsKey(DEV_USER);

            if (hasDevUser) {
                return authenticationClient.post()
                        .uri(TOKEN_ENDPOINT)
                        .body(BodyInserters.fromFormData(buildFormValues(oAuth2ClientProperties)))
                        .retrieve()
                        .bodyToMono(String.class)
                        .map(JSONObject::new)
                        .map(jsonObject -> CastUtils.<String>cast(jsonObject.get("access_token")))
                        .log("Setting token for an unauthenticated request", Level.INFO)
                        .flatMap(token -> {
                            final var  modifiedHeaders = new HttpHeaders();
                            modifiedHeaders.set(HttpHeaders.AUTHORIZATION, BEARER + SPACE + token);

                            ServerHttpRequest serverHttpRequest = exchange.getRequest()
                                    .mutate()
                                    .headers(httpHeaders -> httpHeaders.addAll(modifiedHeaders))
                                    .build();

                            ServerWebExchange serverWebExchange = exchange.mutate()
                                    .request(serverHttpRequest)
                                    .build();

                            return chain.filter(serverWebExchange);
                        });
            }

            return chain.filter(exchange);
        };
    }

    public LinkedMultiValueMap<String, String> buildFormValues(OAuth2ClientProperties oAuth2ClientProperties) {
        Assert.notNull(oAuth2ClientProperties.getClientId(), "client_id can't be null");
        Assert.notNull(oAuth2ClientProperties.getClientSecret(), "secret can't be null");
        LinkedMultiValueMap<String, String> formValues = new LinkedMultiValueMap<>();
        formValues.add(GRANT_TYPE, CLIENT_CREDENTIALS);
        formValues.add(CLIENT_ID, oAuth2ClientProperties.getClientId());
        formValues.add(CLIENT_SECRET, oAuth2ClientProperties.getClientSecret());
        return formValues;
    }
}
