package com.crcl.am.dto;

import com.crcl.am.annotation.UniqueEmail;
import com.crcl.am.annotation.UniqueUserName;
import com.crcl.am.views.UserView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    @NotBlank
    @JsonView(value = UserView.UserResponseView.class)
    private String firstName;
    @NotBlank
    @JsonView(value = UserView.UserResponseView.class)
    private String lastName;
    @NotBlank
    @UniqueUserName
    @JsonView(value = UserView.UserResponseView.class)
    private String username;
    @Email
    @UniqueEmail
    @JsonView(value = UserView.UserResponseView.class)
    private String email;
    @NotBlank
    @JsonView(value = UserView.CreateUserView.class)
    private String password;
    @JsonView(value = UserView.UserPrivateView.class)
    private Set<RoleDto> roles = new HashSet<>();
    @NotBlank
    @JsonView(value = UserView.UserResponseView.class)
    private String gender;
    @JsonView(value = UserView.UserResponseView.class)
    private String avatar;

    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;

    @JsonIgnore
    public boolean isAdmin() {
        return this.roles.stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

    @JsonIgnore
    public boolean isSuperAdmin() {
        return this.roles.stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }
}
