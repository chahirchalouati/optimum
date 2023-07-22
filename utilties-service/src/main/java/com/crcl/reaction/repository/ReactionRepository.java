package com.crcl.reaction.repository;

import com.crcl.reaction.domain.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReactionRepository extends MongoRepository<Reaction, String> {
}
