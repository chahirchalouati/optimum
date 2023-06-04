package com.crcl.am.migration;

import com.crcl.am.domain.Gender;
import com.crcl.am.domain.GramifyPermission;
import com.crcl.am.domain.GramifyRole;
import com.crcl.am.domain.GramifyUser;
import com.crcl.am.repository.RoleRepository;
import com.crcl.am.repository.UserRepository;
import com.crcl.am.utils.DefaultRoles;
import com.crcl.am.utils.ProfileUtils;
import com.crcl.common.exceptions.RoleNotFoundException;
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

    private Function<GramifyRole, GramifyUser> createAdmin() {
        final Set<GramifyPermission> gramifyPermissions = Set.of();
        return role -> {
            role.setPermissions(gramifyPermissions);
            return new GramifyUser()
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
