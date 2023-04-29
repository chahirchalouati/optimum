package com.crcl.authentication.domain;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;


@Document(collection = "clients")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Client {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Collection<ClientAuthenticationMethod> clientAuthenticationMethods = Collections.emptyList();
    private Collection<AuthorizationGrantType> authorizationGrantTypes = Collections.emptyList();
    private Collection<String> redirectUris = Collections.emptyList();
    private Collection<String> scopes = Collections.emptyList();
    private ClientSettings clientSettings;
    private TokenSettings tokenSettings;


}
