package com.crcl.friend_ship.repository;

import com.crcl.friend_ship.domain.FileRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecordRepository extends ReactiveMongoRepository<FileRecord, String> {
}
