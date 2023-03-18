package com.crcl.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class RoleDto {

    private String id;
    private boolean enabled = true;
    private String name;
    private Set<PermissionDto> permissions = new HashSet<>();

}
