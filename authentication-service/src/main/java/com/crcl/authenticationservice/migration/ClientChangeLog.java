package com.crcl.authenticationservice.migration;

import com.crcl.authenticationservice.domain.Client;
import com.crcl.authenticationservice.repository.MongoClientRepository;
import com.crcl.authenticationservice.utils.AppClientScopes;
import com.crcl.authenticationservice.utils.DefaultScopes;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Set;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_POST;

@ChangeLog
public class ClientChangeLog {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ChangeSet(order = "001", id = "save_post_client", author = "@chahir_chalouati")
    public void savePostClient(MongoClientRepository clientRepository) {
        final var redirectUris = Set.of(
                getRedirectUri("127.0.0.1", 7005, "post-client", false),
                "http://127.0.0.1:7005/authorized");
        final var scopes = Set.of(DefaultScopes.OPENID);
        Client client = new Client()
                .setClientId("post-client")
                .setClientSecret(passwordEncoder.encode("secret"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(CLIENT_CREDENTIALS))
                .setRedirectUris(redirectUris).setScopes(scopes);
        clientRepository.save(client);
    }

    @ChangeSet(order = "002", id = "save_proxy_client", author = "@chahir_chalouati")
    public void saveProxyClient(MongoClientRepository clientRepository) {
        final var redirectUris = Set.of(
                "http://127.0.0.1:4200/login/oauth2/code/ui-client-client-oidc",
                "http://127.0.0.1:4200/authorized",
                "http://127.0.0.1:9999/login/oauth2/code/proxy-client-oidc",
                "http://127.0.0.1:9999/authorized");
        final var scopes = Set.of(DefaultScopes.OPENID);
        Client client = new Client()
                .setClientId("proxy-client")
                .setClientSecret(passwordEncoder.encode("secret"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.REFRESH_TOKEN))
                .setRedirectUris(redirectUris)
                .setScopes(scopes);
        clientRepository.save(client);
    }

    @ChangeSet(order = "003", id = "save_order_client", author = "@chahir_chalouati")
    public void saveOrderClient(MongoClientRepository clientRepository) {
        final var redirectUris = Set.of("https://oidcdebugger.com/debug");
        final var scopes = Set.of(DefaultScopes.OPENID);
        Client client = new Client()
                .setClientId("order-client")
                .setClientSecret(passwordEncoder.encode("secret"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(CLIENT_CREDENTIALS))
                .setRedirectUris(redirectUris)
                .setScopes(scopes);
        clientRepository.save(client);
    }

    @ChangeSet(order = "003", id = "save_notification_client", author = "@chahir_chalouati")
    public void saveNotificationUiClient(MongoClientRepository clientRepository) {
        final var redirectUris = Set.of(
                "http://127.0.0.1:7008/login/oauth2/code/ui-client-client-oidc",
                "http://127.0.0.1:7008/authorized");
        Client client = new Client()
                .setClientId("notification_client")
                .setClientSecret(passwordEncoder.encode("secret"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(CLIENT_CREDENTIALS))
                .setRedirectUris(redirectUris)
                .setScopes(AppClientScopes.UI_SCOPES);
        clientRepository.save(client);
    }

    @ChangeSet(order = "003", id = "save_post_ui_client", author = "@chahir_chalouati")
    public void savePostUiClient(MongoClientRepository clientRepository) {
        final var redirectUris = Set.of("http://127.0.0.1:4200/login/oauth2/code/ui-client-client-oidc",
                "http://127.0.0.1:4200/authorized");
        final var scopes = Set.of(DefaultScopes.OPENID);
        Client client = new Client()
                .setClientId("ui-client")
                .setClientSecret(passwordEncoder.encode("secret"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.REFRESH_TOKEN))
                .setRedirectUris(redirectUris)
                .setScopes(AppClientScopes.UI_SCOPES);
        clientRepository.save(client);
    }

    @NotNull
    private static String getRedirectUri(String ip, int port, String clientName, boolean isOidc) {
        if (isOidc)
            return "http://%s:%d/login/oauth2/code/%s-oidc".formatted(ip, port, clientName);
        return "http://%s:%d/login/oauth2/code/%s".formatted(ip, port, clientName);
    }
}
