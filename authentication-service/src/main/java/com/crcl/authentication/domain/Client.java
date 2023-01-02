package com.crcl.authentication.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@Document(collection = "clients")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class Client {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods = new HashSet<>();
    private Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>();
    private Set<String> redirectUris = new HashSet<>();
    private Set<String> scopes = new HashSet<>();
    private ClientSettings clientSettings;
    private TokenSettings tokenSettings;


}
