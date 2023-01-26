package com.crcl.authentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.util.Optional;

public interface MongoOAuth2AuthorizationRepository extends MongoRepository<OAuth2Authorization, String> {
    Optional<OAuth2Authorization> findByAccessToken(String accessTokenValue);
}
