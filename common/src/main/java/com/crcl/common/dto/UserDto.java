package com.crcl.common.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Set<Object> roles = new HashSet<>();
    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isAccountNonLocked = true;
}
