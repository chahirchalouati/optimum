package com.crcl.authenticationservice.repository;


import com.crcl.authenticationservice.domain.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findByNameIgnoreCase(String name);

}
