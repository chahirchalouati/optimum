package com.crcl.authentication.migration;

import com.crcl.authentication.domain.GramifyRole;
import com.crcl.authentication.repository.RoleRepository;
import com.crcl.authentication.utils.DefaultRoles;
import com.crcl.authentication.utils.RoleUtils;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

@ChangeLog(order = "1")
public class RolesSetupChangeLog {

    @ChangeSet(order = "001", id = "add_role_admin", author = "@chahir_chalouati")
    public void createRoleAdmin(RoleRepository roleRepository) {
        GramifyRole gramifyRole = new GramifyRole(DefaultRoles.ROLE_ADMIN);
        roleRepository.insert(gramifyRole);
    }

    @ChangeSet(order = "003", id = "add_roles_user", author = "@chahir_chalouati")
    public void createRoles(RoleRepository roleRepository) {
        roleRepository.insert(RoleUtils.getDefaultUserRoles());
    }
}
