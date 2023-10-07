package com.crcl.authentication.repository;

import com.crcl.authentication.domain.KeyFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeyFileRepository extends MongoRepository<KeyFile, String> {

    KeyFile findFirstByEnabledOrderByCreationDateDesc(boolean enabled);
}

