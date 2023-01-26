package com.crcl.storage.repository;

import com.crcl.storage.domain.FileRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecordRepository extends ReactiveMongoRepository<FileRecord, String> {
}
