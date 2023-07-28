package com.crcl.core.dto;

import lombok.Data;

@Data
public class PermissionDto {

    private String id;
    private String name;
    private boolean enabled = true;

}
