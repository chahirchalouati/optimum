package com.crcl.authentication.migration;

import com.crcl.authentication.configuration.props.Registration;
import com.crcl.authentication.configuration.props.UsersDevelopProperties;
import com.crcl.authentication.domain.Client;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.helpers.MigrationHelper;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.utils.AppClientScopes;
import com.crcl.authentication.utils.RoleUtils;
import com.crcl.authentication.utils.UserGenerator;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.List;
import java.util.Set;

import static com.crcl.authentication.migration.OAuth2ClientMigration.getClient;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_POST;

@Profile("dev")
@Slf4j
@ChangeLog
public class DevelopmentEnvironmentMigration {

    @ChangeSet(order = "001", id = "save_default_clients", author = "@chahir_chalouati")
    public void saveClients(final MigrationHelper migrationHelper) {
        migrationHelper.getSecurityProperties().getRegistrations()
                .forEach((key, registration) -> {
                    final Client client = buildClient(migrationHelper, registration);
                    migrationHelper.getClientRepository().save(client);
                });
    }

    @ChangeSet(order = "002", id = "save_system_clients", author = "@chahir_chalouati")
    public void saveSystemClient(final MigrationHelper migrationHelper) {
        final Client client = new Client()
                .setId("SYSTEM")
                .setClientId("SYSTEM")
                .setClientSecret(migrationHelper.getPasswordEncoder().encode("SYSTEM"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.CLIENT_CREDENTIALS))
                .setRedirectUris(Set.of())
                .setScopes(AppClientScopes.UI_SCOPES);
        migrationHelper.getClientRepository().save(client);
    }

    @ChangeSet(order = "003", id = "save_dummy_users", author = "@chahir_chalouati")
    public void saveDummyUsers(final MigrationHelper migrationHelper) {
        UserRepository userRepository = migrationHelper.getUserRepository();
        if (userRepository.count() <= 1) {
            log.info("start bootstrapping users for development environment");
            UsersDevelopProperties properties = migrationHelper.getUsersDevelopProperties();
            PasswordEncoder passwordEncoder = migrationHelper.getPasswordEncoder();
            final String encodedPassword = passwordEncoder.encode(properties.getPassword());
            List<User> users = UserGenerator.generateRandomUsers(
                            properties.getCount(),
                            properties.getUsername(),
                            encodedPassword)
                    .stream()
                    .map(this::addDefaultRoles)
                    .peek(user -> log.info("created new user {}", user.getUsername()))
                    .toList();
            userRepository.saveAll(users);
            log.info("end bootstrapping users for development environment");
        }
    }

    private User addDefaultRoles(User user) {
        return user.setRoles(RoleUtils.getDefaultUserRoles());
    }

    private Client buildClient(MigrationHelper migrationHelper, Registration registration) {
        registration.getUris().add("https://oauth.pstmn.io/v1/callback");
        return getClient(migrationHelper, registration);
    }
}
