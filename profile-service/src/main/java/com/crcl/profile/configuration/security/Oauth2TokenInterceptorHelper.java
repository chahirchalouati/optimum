package com.crcl.profile.configuration.security;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.*;

@Component
@RequiredArgsConstructor
public class Oauth2TokenInterceptorHelper {
    private final RestTemplate restTemplate;
    private final OAuth2ClientProperties oAuth2ClientProperties;
    private final AuthorizationProperties authorizationProperties;


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
            var response = restTemplate.exchange(authorizationProperties.getTokenUrl(), HttpMethod.POST, request, String.class);
            return new JSONObject(response.getBody()).getString("access_token");
        } catch (JSONException e) {
            return StringUtils.EMPTY;
        }
    }

    public String getAccessTokenForCurrentClient() {
        return this.getClientAccessToken(oAuth2ClientProperties.getClientId(), oAuth2ClientProperties.getClientSecret());
    }
}
