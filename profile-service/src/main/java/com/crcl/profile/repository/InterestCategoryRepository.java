package com.crcl.profile.repository;

import com.crcl.profile.domain.InterestCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InterestCategoryRepository extends MongoRepository<InterestCategory, String> {
    Optional<InterestCategory> findByName(String username);
}
