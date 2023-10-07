package com.crcl.audit.service.impl;

import com.crcl.audit.domain.Audit;
import com.crcl.audit.repository.AuditRepository;
import com.crcl.audit.service.AuditService;
import com.crcl.core.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    @Override
    public Audit save(Audit audit) {
        return auditRepository.save(audit);
    }

    @Override
    public List<Audit> saveAll(List<Audit> audits) {
        return audits.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        auditRepository.deleteById(id);
    }

    @Override
    public Audit findById(String id) {
        return auditRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Audit> findAll() {
        return List.of();
    }

    @Override
    public Page<Audit> findAll(Pageable pageable) {
        return auditRepository.findAll(pageable);
    }

    @Override
    public Audit update(Audit audit, String id) {
        return auditRepository.findById(id)
                .map(stored -> auditRepository.save(audit))
                .orElseThrow(EntityNotFoundException::new);
    }
}
