package com.crcl.authentication.utils.assertions;

import com.crcl.authentication.dto.UserDto;
import org.assertj.core.api.AbstractAssert;

public class UserDtoAssertion extends AbstractAssert<UserDtoAssertion, UserDto> {

    private UserDtoAssertion(UserDto userDto) {
        super(userDto, UserDtoAssertion.class);
    }

    public static UserDtoAssertion assertThat(UserDto userDto) {
        return new UserDtoAssertion(userDto);
    }

    public UserDtoAssertion hasUsername(String expectedUsername) {
        isNotNull();
        String actualUsername = actual.getUsername();
        if (!actualUsername.equals(expectedUsername)) {
            failWithMessage("Expected username to be <%s> but was <%s>", expectedUsername, actualUsername);
        }
        return this;
    }

    public UserDtoAssertion hasPassword(String password) {
        isNotNull();
        String actualPassword = actual.getPassword();
        if (!actualPassword.equals(password)) {
            failWithMessage("Expected password to be <%s> but was <%s>", password, actualPassword);
        }
        return this;
    }
}
