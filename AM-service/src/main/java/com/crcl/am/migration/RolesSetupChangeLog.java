package com.crcl.am.migration;

import com.crcl.am.domain.GramifyRole;
import com.crcl.am.repository.RoleRepository;
import com.crcl.am.utils.DefaultRoles;
import com.crcl.am.utils.RoleUtils;
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
