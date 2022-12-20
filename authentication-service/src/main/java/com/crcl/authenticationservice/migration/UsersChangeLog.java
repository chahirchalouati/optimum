package com.crcl.authenticationservice.migration;

import com.crcl.authenticationservice.domain.Permission;
import com.crcl.authenticationservice.domain.Role;
import com.crcl.authenticationservice.domain.User;
import com.crcl.authenticationservice.exceptions.RoleNotFoundException;
import com.crcl.authenticationservice.repository.RoleRepository;
import com.crcl.authenticationservice.repository.UserRepository;
import com.crcl.authenticationservice.utils.DefaultRoles;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.function.Function;

@ChangeLog(order = "2")
public class UsersChangeLog {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ChangeSet(order = "001", id = "add_super_admin", author = "@chahir_chalouati")
    public void getSuperAdmin(UserRepository userRepository, RoleRepository roleRepository) {
        roleRepository.findByName(DefaultRoles.ROLE_SUPER_ADMIN)
                .map(getSuperAdmin())
                .map(userRepository::save)
                .orElseThrow(() -> new RoleNotFoundException(" unable to find role %s".formatted(DefaultRoles.ROLE_SUPER_ADMIN)));

    }

    private Function<Role, User> getSuperAdmin() {
        return role ->
        {
            final Set<Permission> permissions = Set.of();
            role.setPermissions(permissions);
            return new User()
                    .setEmail("super_admin@mail.com")
                    .setUsername("admin")
                    .setFirstName("admin")
                    .setLastName("admin")
                    .setRoles(Set.of(role))
                    .setPassword(passwordEncoder.encode("admin"));

        };
    }

}
