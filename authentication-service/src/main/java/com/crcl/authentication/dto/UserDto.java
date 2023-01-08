package com.crcl.authentication.dto;

import com.crcl.authentication.annotation.UniqueEmail;
import com.crcl.authentication.annotation.UniqueUserName;
import com.crcl.authentication.views.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @JsonView(value = UserView.UserResponseView.class)
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
}
