package com.crcl.authentication.utils.assertions;

import com.crcl.authentication.domain.User;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class UserAssertion extends AbstractAssert<UserAssertion, User> {

    private UserAssertion(User user) {
        super(user, UserAssertion.class);
    }

    public static UserAssertion assertThat(User user) {
        return new UserAssertion(user);
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

    public UserAssertion hasId() {
        isNotNull();
        if (Objects.isNull(actual.getId())) {
            failWithMessage("User id is null");
        }
        return this;
    }
}
