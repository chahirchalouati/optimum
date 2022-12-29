package com.crcl.authenticationservice.migration;

import com.crcl.authenticationservice.domain.Role;
import com.crcl.authenticationservice.repository.RoleRepository;
import com.crcl.authenticationservice.utils.DefaultRoles;
import com.crcl.authenticationservice.utils.RoleUtils;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

@ChangeLog(order = "1")
public class RolesChangeLog {
    @ChangeSet(order = "001", id = "add_role_super_admin", author = "@chahir_chalouati")
    public void createRoleSuperAdmin(RoleRepository roleRepository) {
        Role role = new Role(DefaultRoles.ROLE_SUPER_ADMIN);
        roleRepository.insert(role);
    }

    @ChangeSet(order = "002", id = "add_role__admin", author = "@chahir_chalouati")
    public void createRoleAdmin(RoleRepository roleRepository) {
        Role role = new Role(DefaultRoles.ROLE_ADMIN);
        roleRepository.insert(role);
    }

    @ChangeSet(order = "003", id = "add_roles_admin", author = "@chahir_chalouati")
    public void createRoles(RoleRepository roleRepository) {
        roleRepository.insert(RoleUtils.getDefaultUserRoles());
    }
}
