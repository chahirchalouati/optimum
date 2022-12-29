package com.crcl.audit.repository;

import com.crcl.audit.domain.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<Audit, String> {

}
