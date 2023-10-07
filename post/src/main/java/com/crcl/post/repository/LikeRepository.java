package com.crcl.post.repository;

import com.crcl.post.domain.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like, String> {
}
