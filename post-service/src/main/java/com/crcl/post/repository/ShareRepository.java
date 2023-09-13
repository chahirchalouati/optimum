package com.crcl.post.repository;

import com.crcl.post.domain.Like;
import com.crcl.post.domain.Share;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShareRepository extends MongoRepository<Share, String> {
}
