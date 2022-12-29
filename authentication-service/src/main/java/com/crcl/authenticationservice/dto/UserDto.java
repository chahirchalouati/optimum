package com.crcl.authenticationservice.dto;

import com.crcl.authenticationservice.annotation.UniqueEmail;
import com.crcl.authenticationservice.annotation.UniqueUserName;
import com.crcl.authenticationservice.domain.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @UniqueUserName
    private String username;
    @Email
    @UniqueEmail
    private String email;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    private boolean isAccountNonExpired = true;
    @JsonIgnore
    private boolean isEnabled = true;
    @JsonIgnore
    private boolean isCredentialsNonExpired = true;
    @JsonIgnore
    private boolean isAccountNonLocked = true;
}
