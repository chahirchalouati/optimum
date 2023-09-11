package com.crcl.authentication.migration;

import com.crcl.authentication.domain.Gender;
import com.crcl.authentication.domain.GramifyPermission;
import com.crcl.authentication.domain.Role;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.repository.RoleRepository;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.utils.DefaultRoles;
import com.crcl.authentication.utils.ProfileUtils;
import com.crcl.core.exceptions.RoleNotFoundException;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.function.Function;

@ChangeLog(order = "2")
public class UsersSetupChangeLog {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ChangeSet(order = "001", id = "add_admin", author = "@chahir_chalouati")
    public void createAdmin(UserRepository userRepository, RoleRepository roleRepository) {
        roleRepository.findByName(DefaultRoles.ROLE_ADMIN)
                .map(createAdmin())
                .map(userRepository::save)
                .orElseThrow(() -> new RoleNotFoundException(" unable to find role %s".formatted(DefaultRoles.ROLE_ADMIN)));
    }

    private Function<Role, User> createAdmin() {
        final Set<GramifyPermission> gramifyPermissions = Set.of();
        return role -> {
            role.setPermissions(gramifyPermissions);
            return new User()
                    .setGender(Gender.MALE)
                    .setEmail("super_admin@mail.com")
                    .setUsername("admin")
                    .setFirstName("admin")
                    .setLastName("admin")
                    .setAvatar(ProfileUtils.DEFAULT_MALE_AVATAR)
                    .setRoles(Set.of(role))
                    .setPassword(passwordEncoder.encode("admin"));
        };
    }

}
