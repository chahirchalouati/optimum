package com.crcl.audit.queue;

import com.crcl.audit.domain.Audit;
import com.crcl.core.dto.requests.AuditRequest;
import com.crcl.core.utils.generic.AuditMapper;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PayloadEventMapper implements AuditMapper<AuditRequest, Audit> {

    @Override
    public AuditRequest toObject(Audit dto) {
        return null;
    }

    @Override
    public Audit toAudit(AuditRequest auditRequest) {
        return new Audit()
                .setPublisher(auditRequest.getUsername())
                .setIdentifier(auditRequest.getIdentifier())
                .setAction(auditRequest.getAction())
                .setDetails(auditRequest.getDetails())
                .setCreateAt(LocalDateTime.now(Clock.systemDefaultZone()));
    }

    @Override
    public List<AuditRequest> mapToObject(List<Audit> dtoList) {
        return null;
    }

    @Override
    public List<Audit> mapToAudit(List<AuditRequest> auditRequests) {
        return null;
    }
}
