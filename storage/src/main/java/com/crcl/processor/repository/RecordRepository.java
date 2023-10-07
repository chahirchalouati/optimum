package com.crcl.processor.repository;

import com.crcl.processor.domain.FileRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecordRepository extends ReactiveMongoRepository<FileRecord, String> {
}
