package com.crcl.profile.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
public class UserDto {
    private String firstName;
    @Getter(AccessLevel.NONE)
    private String fullName;
    private String lastName;
    private String username;
    private String email;
    private String gender;

    public String getFullName() {
        if (Objects.nonNull(firstName) && Objects.nonNull(lastName))
            return firstName + " " + lastName;
        return null;
    }
}

