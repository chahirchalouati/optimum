package com.crcl.am.service.impl;

import com.crcl.am.configuration.props.SecurityProperties;
import com.crcl.am.domain.GramifyClient;
import com.crcl.common.utils.CrclObjectUtils;
import com.crcl.common.utils.generic.Enhancer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

@Component("clientSettingsEnhancer")
@AllArgsConstructor
@Slf4j
public class ClientSettingsEnhancer implements Enhancer<GramifyClient> {
    private final SecurityProperties securityProperties;

    @Override
    public GramifyClient enhance(final GramifyClient gramifyClient) {
        var registrations = securityProperties.getRegistrations();
        var registration = registrations.get(gramifyClient.getClientId());
        if (Objects.isNull(registration)) return gramifyClient;

        var tokenSettings = TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofSeconds(registration.getTokenAccessTimeToLeave()))
                .refreshTokenTimeToLive(Duration.ofSeconds(registration.getRefreshTokenAccessTimeToLeave()))
                .build();

        CrclObjectUtils.setIfNotNull(registration.getId(), gramifyClient::setClientId);
        CrclObjectUtils.setIfNotNull(tokenSettings, gramifyClient::setTokenSettings);

        return gramifyClient;
    }


}
