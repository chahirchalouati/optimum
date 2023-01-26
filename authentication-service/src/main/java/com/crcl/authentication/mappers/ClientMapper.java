package com.crcl.authentication.mappers;

import com.crcl.authentication.domain.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ClientMapper {
    public Client toClient(RegisteredClient client) {
        Client target = new Client();
        BeanUtils.copyProperties(client, target);
        return target;
    }

    public RegisteredClient toRegisteredClient(Client client) {
        return RegisteredClient.withId(client.getId())
                .clientId(client.getClientId())
                .clientAuthenticationMethods(cons -> cons.addAll(client.getClientAuthenticationMethods()))
                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(client.getAuthorizationGrantTypes()))
                .redirectUris(uris -> uris.addAll(client.getRedirectUris()))
                .scopes(scopes -> scopes.addAll(client.getScopes()))
                .clientSecret(client.getClientSecret())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(1)).refreshTokenTimeToLive(Duration.ofHours(10)).build())
                .clientSettings(client.getClientSettings())
                .clientSecretExpiresAt(client.getClientSecretExpiresAt())
                .build();
    }
}
