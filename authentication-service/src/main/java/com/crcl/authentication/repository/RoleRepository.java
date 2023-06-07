package com.crcl.authentication.repository;


import com.crcl.authentication.domain.GramifyRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<GramifyRole, String> {
    Optional<GramifyRole> findByName(String name);

}
