package com.crcl.authentication.configuration;

import com.crcl.authentication.mappers.RoleMapper;
import com.crcl.authentication.mappers.RoleMapperImpl;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.mappers.UserMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MappersConfiguration {
    @Bean
    protected UserMapper userMapper(final RoleMapper roleMapper) {
        return new UserMapperImpl(roleMapper);
    }

    @Bean
    protected RoleMapper roleMapper() {
        return new RoleMapperImpl();
    }
}
