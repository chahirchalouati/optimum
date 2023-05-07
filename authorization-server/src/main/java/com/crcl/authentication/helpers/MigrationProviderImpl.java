package com.crcl.authentication.helpers;

import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.configuration.props.UsersDevelopProperties;
import com.crcl.authentication.repository.MongoClientRepository;
import com.crcl.authentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
@Getter
public class MigrationProviderImpl implements MigrationProvider {
    private final SecurityProperties securityProperties;
    private final MongoClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersDevelopProperties usersDevelopProperties;
}
