package com.crcl.post.repository;

import com.crcl.post.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {
    @Query("{'images.id': ?0}")
    Optional<Post> findByImageId(String id);
}
