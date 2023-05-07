package com.crcl.profile.repository;

import com.crcl.profile.domain.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SkillRepository extends MongoRepository<Skill, String> {
    Optional<Skill> findByName(String username);

    boolean existsByNameIgnoreCase(String username);
}
