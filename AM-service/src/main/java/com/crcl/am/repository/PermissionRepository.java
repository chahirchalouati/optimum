package com.crcl.am.repository;


import com.crcl.am.domain.GramifyPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PermissionRepository extends MongoRepository<GramifyPermission, String> {
    Optional<GramifyPermission> findByNameIgnoreCase(String name);

}
