package com.crcl.post.repository;

import com.crcl.post.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends MongoRepository<Tag, String> {
    List<Tag> findByNameIn(Set<String> names);

    Set<Tag> findByEntityId(String id);
}
