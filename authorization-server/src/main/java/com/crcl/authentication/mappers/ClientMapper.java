package com.crcl.authentication.mappers;

import com.crcl.authentication.domain.GramifyClient;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ClientMapper {
    public GramifyClient toClient(RegisteredClient client) {
        GramifyClient target = new GramifyClient();
        BeanUtils.copyProperties(client, target);
        return target;
    }

    public RegisteredClient toRegisteredClient(GramifyClient gramifyClient) {
        return RegisteredClient.withId(gramifyClient.getId())
                .clientId(gramifyClient.getClientId())
                .clientAuthenticationMethods(cons -> cons.addAll(gramifyClient.getClientAuthenticationMethods()))
                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(gramifyClient.getAuthorizationGrantTypes()))
                .redirectUris(uris -> uris.addAll(gramifyClient.getRedirectUris()))
                .scopes(scopes -> scopes.addAll(gramifyClient.getScopes()))
                .clientSecret(gramifyClient.getClientSecret())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(1)).refreshTokenTimeToLive(Duration.ofHours(10)).build())
                .clientSettings(gramifyClient.getClientSettings())
                .clientSecretExpiresAt(gramifyClient.getClientSecretExpiresAt())
                .build();
    }
}
