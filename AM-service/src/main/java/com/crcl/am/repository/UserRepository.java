package com.crcl.am.repository;

import com.crcl.am.domain.GramifyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends MongoRepository<GramifyUser, String>, CustomUserRepository {
    Optional<GramifyUser> findByUsernameAllIgnoreCase(@NonNull String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCase(String username);

    List<GramifyUser> findByUsernameIn(Set<String> userNames);
}
