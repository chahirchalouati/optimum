package com.crcl.authentication.migration;

import com.crcl.authentication.domain.User;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.utils.UserGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@AllArgsConstructor
@Component
@Profile("dev")
public class UsersDevelopMigrations {

    private final UserRepository userRepository;

    @PostConstruct
    public void migrate() {
        if (userRepository.count() < 10) {
            List<User> users = UserGenerator.generateRandomUsers(10, "username");
            userRepository.saveAll(users);
        }
    }

}
