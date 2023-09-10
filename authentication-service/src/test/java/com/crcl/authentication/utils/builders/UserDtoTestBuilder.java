package com.crcl.authentication.utils.builders;

import com.crcl.authentication.dto.RoleDto;
import com.crcl.authentication.dto.UserDto;

import java.util.HashSet;
import java.util.Set;

import java.util.HashSet;
import java.util.Set;

public class UserDtoTestBuilder {
    private UserDto userDto;

    private UserDtoTestBuilder() {
        userDto = new UserDto();
    }

    public static UserDtoTestBuilder createUserDto() {
        return new UserDtoTestBuilder();
    }

    public UserDtoTestBuilder withFirstName(String firstName) {
        userDto.setFirstName(firstName);
        return this;
    }

    public UserDtoTestBuilder withLastName(String lastName) {
        userDto.setLastName(lastName);
        return this;
    }

    public UserDtoTestBuilder withUsername(String username) {
        userDto.setUsername(username);
        return this;
    }

    public UserDtoTestBuilder withEmail(String email) {
        userDto.setEmail(email);
        return this;
    }

    public UserDtoTestBuilder withPassword(String password) {
        userDto.setPassword(password);
        return this;
    }

    public UserDtoTestBuilder withRoles(Set<RoleDto> roles) {
        userDto.setRoles(roles);
        return this;
    }

    public UserDtoTestBuilder withGender(String gender) {
        userDto.setGender(gender);
        return this;
    }

    public UserDtoTestBuilder withAvatar(String avatar) {
        userDto.setAvatar(avatar);
        return this;
    }

    public UserDtoTestBuilder withAccountNonExpired(boolean accountNonExpired) {
        userDto.setAccountNonExpired(accountNonExpired);
        return this;
    }

    public UserDtoTestBuilder withEnabled(boolean enabled) {
        userDto.setEnabled(enabled);
        return this;
    }

    public UserDtoTestBuilder withCredentialsNonExpired(boolean credentialsNonExpired) {
        userDto.setCredentialsNonExpired(credentialsNonExpired);
        return this;
    }

    public UserDtoTestBuilder withAccountNonLocked(boolean accountNonLocked) {
        userDto.setAccountNonLocked(accountNonLocked);
        return this;
    }

    public UserDto build() {
        return userDto;
    }

    public UserDtoTestBuilder withAccountNonExpired() {
        return this.withAccountNonExpired(true);
    }

    public UserDtoTestBuilder withDefaultAvatar() {
        return this.withAvatar("http://avatar-default.com/test");
    }
}


