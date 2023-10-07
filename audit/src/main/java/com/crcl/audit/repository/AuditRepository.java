package com.crcl.audit.repository;

import com.crcl.audit.domain.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AuditRepository extends MongoRepository<Audit, String> {
    @Override
    @Query("{deletedAt: {$exists: false}}")
    Page<Audit> findAll(Pageable pageable);
}
