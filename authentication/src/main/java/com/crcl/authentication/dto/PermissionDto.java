package com.crcl.authentication.dto;

import lombok.Data;

@Data
public class PermissionDto {
    private boolean enabled = true;
    private String name;
}
