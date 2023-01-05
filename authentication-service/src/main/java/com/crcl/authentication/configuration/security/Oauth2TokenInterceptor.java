package com.crcl.authentication.configuration.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static java.util.Objects.isNull;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.*;

@Slf4j
public class Oauth2TokenInterceptor implements RequestInterceptor {
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String BEARER_TOKEN_TYPE = "Bearer";
    private final RestTemplate restTemplate;
    private final String IDP_URL;

    public Oauth2TokenInterceptor(RestTemplate restTemplate, String IDP_URL) {
        this.restTemplate = restTemplate;
        this.IDP_URL = IDP_URL;
    }

    @Override
    public void apply(RequestTemplate template) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isNull(authentication)) return;
        if (authentication instanceof JwtAuthenticationToken jwt) {
            var token = jwt.getToken().getTokenValue();
            template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
        }
        if (authentication instanceof OAuth2ClientAuthenticationToken clientAuthToken) {
            var clientId = Objects.requireNonNull(clientAuthToken.getRegisteredClient()).getClientId();
            var password = ((String) clientAuthToken.getCredentials());
            var token = this.getClientAccessToken(clientId, password);
            if (!StringUtils.isBlank(token)) {
                template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
            }
        }
    }

    private String getClientAccessToken(String clientId, String password) {
        try {
            if (StringUtils.isBlank(clientId) || StringUtils.isBlank(password)) return StringUtils.EMPTY;
            var formValues = new LinkedMultiValueMap<>();
            formValues.add(GRANT_TYPE, CLIENT_CREDENTIALS.getValue());
            formValues.add(CLIENT_ID, clientId);
            formValues.add(CLIENT_SECRET, password);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<?> request = new HttpEntity<>(formValues, headers);
            var response = restTemplate
                    .exchange("%s/oauth2/token".formatted(IDP_URL), HttpMethod.POST, request, String.class);
            return new JSONObject(response.getBody()).getString("access_token");
        } catch (JSONException e) {
            return StringUtils.EMPTY;
        }
    }
}

