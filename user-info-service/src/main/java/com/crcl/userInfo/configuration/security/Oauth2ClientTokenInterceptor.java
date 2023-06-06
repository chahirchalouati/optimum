package com.crcl.userInfo.configuration.security;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.crcl.userInfo.configuration.security.ServerRequestInterceptor.BEARER_TOKEN_TYPE;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.*;

@RequiredArgsConstructor
public class Oauth2ClientTokenInterceptor implements ReactiveHttpRequestInterceptor {
    private final RestTemplate restTemplate;
    private final OAuth2ClientProperties oAuth2ClientProperties;
    @Value("${client.authentication.url}")
    private String IDP_URL;

    @Override
    public Mono<ReactiveHttpRequest> apply(ReactiveHttpRequest reactiveHttpRequest) {
        String clientId = oAuth2ClientProperties.getClientId();
        String clientSecret = oAuth2ClientProperties.getClientSecret();
        String accessToken = getClientAccessToken(clientId, clientSecret);
        reactiveHttpRequest.headers().put(HttpHeaders.AUTHORIZATION, List.of("%s %s".formatted(BEARER_TOKEN_TYPE, accessToken)));

        return Mono.just(reactiveHttpRequest);
    }

    public String getClientAccessToken(String clientId, String password) {
        try {
            if (StringUtils.isBlank(clientId) || StringUtils.isBlank(password)) return StringUtils.EMPTY;
            var formValues = new LinkedMultiValueMap<>();
            formValues.add(GRANT_TYPE, CLIENT_CREDENTIALS.getValue());
            formValues.add(CLIENT_ID, clientId);
            formValues.add(CLIENT_SECRET, password);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<?> request = new HttpEntity<>(formValues, headers);
            var response = restTemplate.exchange("%s/oauth2/token".formatted(IDP_URL), HttpMethod.POST, request, String.class);
            return new JSONObject(response.getBody()).getString("access_token");
        } catch (JSONException e) {
            return StringUtils.EMPTY;
        }
    }
}
