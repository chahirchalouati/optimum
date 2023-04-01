package com.crcl.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private Set<RoleDto> roles = new HashSet<>();
    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isAccountNonLocked = true;

    public List<String> getUserRoles() {
        return this.getRoles().stream()
                .filter(RoleDto::isEnabled)
                .map(mapToAuthorities())
                .flatMap(List::stream)
                .toList();
    }

    private Function<RoleDto, List<String>> mapToAuthorities() {
        return role -> {
            var authorities = new ArrayList<String>();
            authorities.add(role.getName());
            for (var permission : role.getPermissions()) {
                if (permission.isEnabled()) {
                    authorities.add(permission.getName());
                }
            }
            return authorities;
        };
    }
}
