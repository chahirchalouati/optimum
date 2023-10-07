package com.crcl.authentication.repository;

import com.crcl.authentication.domain.Gender;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.utils.assertions.UserAssertion;
import com.crcl.authentication.utils.builders.UserTestBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void saveUsers() {
        User user = UserTestBuilder.createUser()
                .withUsername("username")
                .withGender(Gender.MALE)
                .build();

        User save = userRepository.save(user);

        UserAssertion.assertThat(save)
                .hasUsername("username")
                .hasId();

    }


}