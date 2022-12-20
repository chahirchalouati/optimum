package com.crcl.authenticationservice.repository;


import com.crcl.authenticationservice.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);

}
