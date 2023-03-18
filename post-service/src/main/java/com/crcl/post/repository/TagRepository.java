package com.crcl.post.repository;

import com.crcl.post.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagRepository extends MongoRepository<Tag, String> {
    List<Tag> findByNameIn(List<String> names);
}
