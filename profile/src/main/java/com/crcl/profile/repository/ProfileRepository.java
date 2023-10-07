package com.crcl.profile.repository;

import com.crcl.profile.domain.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByUsername(String username);

    List<Profile> findByUsernameIn(List<String> usernames);

    boolean existsByUsernameIgnoreCase(String username);
}
