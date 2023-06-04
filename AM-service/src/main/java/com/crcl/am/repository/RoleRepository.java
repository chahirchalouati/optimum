package com.crcl.am.repository;


import com.crcl.am.domain.GramifyRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<GramifyRole, String> {
    Optional<GramifyRole> findByName(String name);

}
