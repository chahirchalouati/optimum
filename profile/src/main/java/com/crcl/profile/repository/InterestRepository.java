package com.crcl.profile.repository;

import com.crcl.profile.domain.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InterestRepository extends MongoRepository<Interest, String> {
    Optional<Interest> findByName(String username);

    boolean existsByNameIgnoreCase(String username);
}
