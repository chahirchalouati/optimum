package com.crcl.am.helpers;

import com.crcl.am.configuration.props.SecurityProperties;
import com.crcl.am.configuration.props.UsersDevelopProperties;
import com.crcl.am.repository.MongoClientRepository;
import com.crcl.am.repository.UserRepository;
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
