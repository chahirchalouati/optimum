package com.crcl.authentication.repository;

import com.crcl.authentication.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
    Optional<User> findByUsernameAllIgnoreCase(@NonNull String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCase(String username);
}
