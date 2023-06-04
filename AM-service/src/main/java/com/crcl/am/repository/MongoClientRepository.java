package com.crcl.am.repository;

import com.crcl.am.domain.GramifyClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoClientRepository extends MongoRepository<GramifyClient, String> {
    Optional<GramifyClient> findByClientId(String clientId);
}
