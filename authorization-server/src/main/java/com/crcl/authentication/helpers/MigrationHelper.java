package com.crcl.authentication.helpers;

import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.configuration.props.UsersDevelopProperties;
import com.crcl.authentication.repository.MongoClientRepository;
import com.crcl.authentication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface MigrationHelper {
    SecurityProperties getSecurityProperties();

    MongoClientRepository getClientRepository();

    PasswordEncoder getPasswordEncoder();

    UserRepository getUserRepository();

    UsersDevelopProperties getUsersDevelopProperties();
}
