package com.crcl.authentication.migration;

import com.crcl.authentication.configuration.props.UsersDevelopProperties;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.utils.RoleUtils;
import com.crcl.authentication.utils.UserGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Profile("dev")
@Slf4j
public class UsersDevelopMigrations {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersDevelopProperties usersDevelopProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void migrate() {
        if (userRepository.count() <= 1) {
            log.info("start bootstrapping users for development environment");
            final String encodedPassword = passwordEncoder.encode(usersDevelopProperties.getPassword());
            List<User> users = UserGenerator.generateRandomUsers(
                            usersDevelopProperties.getCount(),
                            usersDevelopProperties.getUsername(),
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
}
