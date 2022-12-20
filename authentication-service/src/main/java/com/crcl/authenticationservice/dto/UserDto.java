package com.crcl.authenticationservice.dto;

import com.crcl.authenticationservice.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<Role> roles = new HashSet<>();
    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;
}
