package com.crcl.userInfo.domain;

import com.crcl.common.dto.RoleDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<RoleDto> roles = new HashSet<>();
    private String gender;
    private String avatar;
    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;
}
