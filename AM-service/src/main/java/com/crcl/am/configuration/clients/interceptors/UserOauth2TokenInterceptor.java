package com.crcl.am.configuration.clients.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Objects;

import static com.crcl.am.configuration.clients.interceptors.Oauth2TokenInterceptorHelper.AUTHORIZATION_HEADER;
import static com.crcl.am.configuration.clients.interceptors.Oauth2TokenInterceptorHelper.BEARER_TOKEN_TYPE;
import static java.util.Objects.isNull;

@Slf4j
@AllArgsConstructor
public class UserOauth2TokenInterceptor implements RequestInterceptor {
    private final Oauth2TokenInterceptorHelper oauth2TokenInterceptorHelper;

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
            var token = oauth2TokenInterceptorHelper.getClientAccessToken(clientId, password);
            if (!StringUtils.isBlank(token)) {
                template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
            }
        }

    }
}

