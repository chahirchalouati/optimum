package com.crcl.authentication.utils.assertions;

import com.crcl.authentication.dto.UserDto;
import org.assertj.core.api.AbstractAssert;

public class UserAssertion extends AbstractAssert<UserAssertion, UserDto> {

    private UserAssertion(UserDto userDto) {
        super(userDto, UserAssertion.class);
    }

    public static UserAssertion assertThat(UserDto userDto) {
        return new UserAssertion(userDto);
    }

    public UserAssertion hasUsername(String expectedUsername) {
        isNotNull();
        String actualUsername = actual.getUsername();
        if (!actualUsername.equals(expectedUsername)) {
            failWithMessage("Expected username to be <%s> but was <%s>", expectedUsername, actualUsername);
        }
        return this;
    }

    public UserAssertion hasPassword(String password) {
        isNotNull();
        String actualPassword = actual.getPassword();
        if (!actualPassword.equals(password)) {
            failWithMessage("Expected password to be <%s> but was <%s>", password, actualPassword);
        }
        return this;
    }
}
