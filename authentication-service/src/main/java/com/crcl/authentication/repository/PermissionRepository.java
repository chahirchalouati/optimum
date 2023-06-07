package com.crcl.authentication.repository;


import com.crcl.authentication.domain.GramifyPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PermissionRepository extends MongoRepository<GramifyPermission, String> {
    Optional<GramifyPermission> findByNameIgnoreCase(String name);

}
