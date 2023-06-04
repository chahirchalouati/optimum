package com.crcl.am.repository;

import com.crcl.am.domain.Key;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Set;

public interface RSAKeyRepository extends MongoRepository<Key, String> {
    @Query("{'isValid': true}")
    Set<Key> findByValid();
}
