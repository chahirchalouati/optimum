package com.crcl.authentication.repository;

import com.crcl.authentication.domain.Key;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Set;

public interface RSAKeyRepository extends MongoRepository<Key, String> {
    @Query("{'isValid': true}")
    Set<Key> findByValid();
}
