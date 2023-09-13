package com.crcl.post.repository;

import com.crcl.post.domain.Like;
import com.crcl.post.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikeRepository extends MongoRepository<Like, String> {
}
