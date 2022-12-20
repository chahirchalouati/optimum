package com.crcl.audit.repository;

import com.crcl.audit.domain.Audit;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuditRepository extends CassandraRepository<Audit, UUID> {
    @Override
    List<Audit> findAll();
}
