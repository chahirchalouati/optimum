package com.crcl.authentication.repository;


import com.crcl.authentication.domain.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findByNameIgnoreCase(String name);

}
