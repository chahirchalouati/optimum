package com.crcl.authentication.utils.builders;

import com.crcl.authentication.domain.Gender;
import com.crcl.authentication.domain.Role;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.mappers.RoleMapperImpl;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.mappers.UserMapperImpl;

import java.util.Set;

public class UserTestBuilder {
    private final RoleMapperImpl roleMapper = new RoleMapperImpl();
    private final UserMapper usermapper = new UserMapperImpl(roleMapper);
    private User user;

    private UserTestBuilder() {
        user = new User();
    }

    private UserTestBuilder(UserDto dto) {
        user = usermapper.toEntity(dto);
    }

    public static UserTestBuilder createUser() {
        return new UserTestBuilder();
    }

    public static UserTestBuilder fromDto(UserDto userDto) {
        return new UserTestBuilder(userDto);
    }

    public UserTestBuilder withFirstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    public UserTestBuilder withLastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    public UserTestBuilder withUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserTestBuilder withEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public UserTestBuilder withPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserTestBuilder withRoles(Set<Role> roles) {
        user.setRoles(roles);
        return this;
    }

    public UserTestBuilder withGender(Gender gender) {
        user.setGender(gender);
        return this;
    }

    public UserTestBuilder withAvatar(String avatar) {
        user.setAvatar(avatar);
        return this;
    }

    public UserTestBuilder withAccountNonExpired(boolean accountNonExpired) {
        user.setAccountNonExpired(accountNonExpired);
        return this;
    }

    public UserTestBuilder withEnabled(boolean enabled) {
        user.setEnabled(enabled);
        return this;
    }

    public UserTestBuilder withCredentialsNonExpired(boolean credentialsNonExpired) {
        user.setCredentialsNonExpired(credentialsNonExpired);
        return this;
    }

    public UserTestBuilder withAccountNonLocked(boolean accountNonLocked) {
        user.setAccountNonLocked(accountNonLocked);
        return this;
    }

    public User build() {
        return user;
    }
}


