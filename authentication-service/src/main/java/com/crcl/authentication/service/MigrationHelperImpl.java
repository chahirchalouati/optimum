package com.crcl.authentication.service;

import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.repository.MongoClientRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Getter
@AllArgsConstructor
public class MigrationHelperImpl implements MigrationHelper {
    private final SecurityProperties securityProperties;
    private final PasswordEncoder passwordEncoder;
    private final MongoClientRepository clientRepository;

}
