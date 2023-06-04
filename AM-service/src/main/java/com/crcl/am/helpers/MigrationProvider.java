package com.crcl.am.helpers;

import com.crcl.am.configuration.props.SecurityProperties;
import com.crcl.am.configuration.props.UsersDevelopProperties;
import com.crcl.am.repository.MongoClientRepository;
import com.crcl.am.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface MigrationProvider {
    SecurityProperties getSecurityProperties();

    MongoClientRepository getClientRepository();

    PasswordEncoder getPasswordEncoder();

    UserRepository getUserRepository();

    UsersDevelopProperties getUsersDevelopProperties();
}
