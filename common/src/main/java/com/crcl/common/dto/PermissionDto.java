package com.crcl.common.dto;

import lombok.Data;

@Data
public class PermissionDto {

    private String id;
    private String name;
    private boolean enabled = true;

}
