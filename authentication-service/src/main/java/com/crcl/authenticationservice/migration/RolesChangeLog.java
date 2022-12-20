package com.crcl.authenticationservice.migration;

import com.crcl.authenticationservice.domain.Role;
import com.crcl.authenticationservice.repository.RoleRepository;
import com.crcl.authenticationservice.utils.DefaultRoles;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ChangeLog(order = "1")
public class RolesChangeLog {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ChangeSet(order = "001", id = "add_role_super_admin", author = "@chahir_chalouati")
    public void createRoleSuperAdmin(RoleRepository roleRepository) {
        Role role = new Role(DefaultRoles.ROLE_SUPER_ADMIN);
        roleRepository.insert(role);
    }

}
