package com.crcl.utilities.repository;

import com.crcl.common.dto.Entity;
import com.crcl.common.dto.requests.ReactionType;
import com.crcl.utilities.domain.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ReactionRepository extends MongoRepository<Reaction, String>, CustomReactionRepository {
    @Query("""
             {
               "entity": ?0,
               "targetId": ?1,
               "reactingUser.username": ?2,
               "reactingUser.email": ?3,
               "type": ?4,
               "deleted":?5
             }
            """)
    Optional<Reaction> findByReaction(Entity entity, String targetId, String username, String email, ReactionType type, boolean deleted);

}
