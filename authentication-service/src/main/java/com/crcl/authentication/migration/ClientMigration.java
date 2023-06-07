package com.crcl.authentication.migration;

import com.crcl.authentication.configuration.props.Registration;
import com.crcl.authentication.domain.GramifyClient;
import com.crcl.authentication.helpers.MigrationProvider;
import com.crcl.authentication.utils.GramifyClientScopes;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.List;
import java.util.Set;

import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_POST;

@Profile({"dev", "docker"})
@ChangeLog
public class ClientMigration {

    protected static GramifyClient getClient(MigrationProvider migrationProvider, Registration registration) {
        List<AuthorizationGrantType> grantTypes = registration.getGrantTypes().stream()
                .map(AuthorizationGrantType::new)
                .toList();

        List<String> redirectUris = registration.getUris().stream()
                .map(uri -> uri.contains("/callback") ? uri : uri.concat("/authorized"))
                .toList();

        return new GramifyClient()
                .setId(registration.getId())
                .setClientId(registration.getId())
                .setClientSecret(migrationProvider.getPasswordEncoder().encode("secret"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(grantTypes)
                .setRedirectUris(redirectUris)
                .setScopes(registration.getScopes());
    }

    @ChangeSet(order = "001", id = "save_default_clients", author = "@chahir_chalouati")
    public void saveClients(MigrationProvider migrationProvider) {
        migrationProvider.getSecurityProperties()
                .getRegistrations()
                .values()
                .stream()
                .map(registration -> buildClient(migrationProvider, registration))
                .forEach(gramifyClient -> migrationProvider.getClientRepository().save(gramifyClient));
    }

    @ChangeSet(order = "002", id = "save_system_clients", author = "@chahir_chalouati")
    public void saveSystemClient(MigrationProvider migrationProvider) {
        GramifyClient gramifyClient = new GramifyClient()
                .setId("SYSTEM")
                .setClientId("SYSTEM")
                .setClientSecret(migrationProvider.getPasswordEncoder().encode("SYSTEM"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.CLIENT_CREDENTIALS))
                .setRedirectUris(Set.of())
                .setScopes(GramifyClientScopes.UI_SCOPES);

        migrationProvider.getClientRepository().save(gramifyClient);
    }

    private GramifyClient buildClient(MigrationProvider migrationProvider, Registration registration) {
        return getClient(migrationProvider, registration);
    }
}
