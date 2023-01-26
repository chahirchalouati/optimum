package com.crcl.authentication.migration;

import com.crcl.authentication.configuration.props.UsersDevelopProperties;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.utils.RoleUtils;
import com.crcl.authentication.utils.UserGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@AllArgsConstructor
@Component
@Profile("dev")
public class UsersDevelopMigrations {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersDevelopProperties usersDevelopProperties;


    @PostConstruct
    public void migrate() {
        String encodedPws = passwordEncoder.encode(usersDevelopProperties.getPassword());
        List<User> users = UserGenerator.generateRandomUsers(
                usersDevelopProperties.getCount(),
                usersDevelopProperties.getUsername(),
                encodedPws
        );
        users.forEach(user -> user.setRoles(RoleUtils.getDefaultUserRoles()));
        userRepository.saveAll(users);

    }


}
