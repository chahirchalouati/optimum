package com.crcl.authentication.service.impl;

import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.domain.Client;
import com.crcl.common.utils.ObjectUtils;
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
    private final SecurityProperties securityProperties;

    @Override
    public Client enhance(final Client client) {
        final var clientsProps = securityProperties.getRegistrationHashMap()
                .get(client.getClientId());
        if (isNull(clientsProps))
            return client;

        final var accessTokenTimeToLive = Duration.ofSeconds(clientsProps.getTokenAccessTimeToLeave());
        final var refreshTokenTimeToLive = Duration.ofSeconds(clientsProps.getRefreshTokenAccessTimeToLeave());
        final var tokenSettings = TokenSettings.builder()
                .accessTokenTimeToLive(accessTokenTimeToLive)
                .refreshTokenTimeToLive(refreshTokenTimeToLive)
                .build();
        ObjectUtils.setIfNotNull(clientsProps.getId(), client::setClientId);
        ObjectUtils.setIfNotNull(tokenSettings, client::setTokenSettings);
        return client;
    }


}
