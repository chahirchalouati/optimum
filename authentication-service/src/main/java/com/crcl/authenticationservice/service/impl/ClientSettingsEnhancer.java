package com.crcl.authenticationservice.service.impl;

import com.crcl.authenticationservice.configuration.props.SecurityProps;
import com.crcl.authenticationservice.domain.Client;
import com.crcl.common.utils.generic.Enhancer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static java.util.Objects.isNull;

@Component("clientSettingsEnhancer")
@AllArgsConstructor
@Slf4j
public class ClientSettingsEnhancer implements Enhancer<Client> {
    private final SecurityProps securityProps;

    @Override
    public Client enhance(final Client client) {
        final var clientsProps = securityProps.getClientsProps()
                .get(client.getClientId());
        if (isNull(clientsProps))
            return client;

        final var accessTokenTimeToLive = Duration.ofSeconds(clientsProps.getTokenAccessTimeToLeave());
        final var refreshTokenTimeToLive = Duration.ofSeconds(clientsProps.getRefreshTokenAccessTimeToLeave());
        final var tokenSettings = TokenSettings.builder()
                .accessTokenTimeToLive(accessTokenTimeToLive)
                .refreshTokenTimeToLive(refreshTokenTimeToLive)
                .build();
        client.setTokenSettings(tokenSettings);

        return client;
    }
}
