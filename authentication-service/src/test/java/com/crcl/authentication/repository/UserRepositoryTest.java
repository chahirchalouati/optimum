package com.crcl.authentication.repository;

import com.crcl.authentication.domain.User;
import com.crcl.authentication.mappers.RoleMapper;
import com.crcl.authentication.mappers.RoleMapperImpl;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.mappers.UserMapperImpl;
import com.crcl.authentication.utils.builders.UserTestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@DataMongoTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUsers() {
        User user = UserTestBuilder.createUser().build();

        User save = userRepository.save(user);

        Assertions.assertNotNull(user);

    }

    @TestConfiguration
    static class MapperConfig {
        @Bean
        UserMapper userMapper(RoleMapper roleMapper) {
            return new UserMapperImpl(roleMapper);
        }

        @Bean
        RoleMapper roleMapper() {
            return new RoleMapperImpl();
        }
    }

}