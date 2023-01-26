package com.crcl.authentication.service;

import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.repository.MongoClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface MigrationHelper {
    SecurityProperties getSecurityProperties();

    MongoClientRepository getClientRepository();

    PasswordEncoder getPasswordEncoder();
}
