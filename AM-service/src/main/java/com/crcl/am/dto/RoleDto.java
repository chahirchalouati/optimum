package com.crcl.am.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDto {
    private boolean enabled = true;
    private String name;
    private Set<PermissionDto> permissions = new HashSet<>();
}
