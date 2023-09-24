package com.crcl.post.repository;

import com.crcl.post.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface TagRepository extends MongoRepository<Tag, String> {

    Set<Tag> findByEntityId(String id);
}
