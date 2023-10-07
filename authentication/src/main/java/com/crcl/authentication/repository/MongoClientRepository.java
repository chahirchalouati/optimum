package com.crcl.authentication.repository;

import com.crcl.authentication.domain.GramifyClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoClientRepository extends MongoRepository<GramifyClient, String> {
    Optional<GramifyClient> findByClientId(String clientId);
}
