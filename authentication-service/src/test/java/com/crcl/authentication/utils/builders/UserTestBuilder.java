package com.crcl.authentication.utils.builders;

import com.crcl.authentication.domain.Gender;
import com.crcl.authentication.domain.GramifyRole;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.mappers.RoleMapperImpl;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.mappers.UserMapperImpl;

import java.util.Set;

public class UserTestBuilder {
    private final RoleMapperImpl roleMapper = new RoleMapperImpl();
    private final UserMapper usermapper = new UserMapperImpl(roleMapper);
    private User userDto;

    private UserTestBuilder() {
        userDto = new User();
    }

    private UserTestBuilder(UserDto dto) {
        userDto= usermapper.toEntity(dto);
    }

    public static UserTestBuilder createUser() {
        return new UserTestBuilder();
    }

    public static UserTestBuilder fromDto(UserDto userDto) {
        return new UserTestBuilder(userDto);
    }

    public UserTestBuilder withFirstName(String firstName) {
        userDto.setFirstName(firstName);
        return this;
    }

    public UserTestBuilder withLastName(String lastName) {
        userDto.setLastName(lastName);
        return this;
    }

    public UserTestBuilder withUsername(String username) {
        userDto.setUsername(username);
        return this;
    }

    public UserTestBuilder withEmail(String email) {
        userDto.setEmail(email);
        return this;
    }

    public UserTestBuilder withPassword(String password) {
        userDto.setPassword(password);
        return this;
    }

    public UserTestBuilder withRoles(Set<GramifyRole> roles) {
        userDto.setRoles(roles);
        return this;
    }

    public UserTestBuilder withGender(Gender gender) {
        userDto.setGender(gender);
        return this;
    }

    public UserTestBuilder withAvatar(String avatar) {
        userDto.setAvatar(avatar);
        return this;
    }

    public UserTestBuilder withAccountNonExpired(boolean accountNonExpired) {
        userDto.setAccountNonExpired(accountNonExpired);
        return this;
    }

    public UserTestBuilder withEnabled(boolean enabled) {
        userDto.setEnabled(enabled);
        return this;
    }

    public UserTestBuilder withCredentialsNonExpired(boolean credentialsNonExpired) {
        userDto.setCredentialsNonExpired(credentialsNonExpired);
        return this;
    }

    public UserTestBuilder withAccountNonLocked(boolean accountNonLocked) {
        userDto.setAccountNonLocked(accountNonLocked);
        return this;
    }

    public User build() {
        return userDto;
    }
}


