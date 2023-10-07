package com.crcl.audit.queue;

import com.crcl.audit.domain.Audit;
import com.crcl.core.dto.requests.AuditEventPayload;
import com.crcl.core.utils.generic.AuditMapper;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PayloadEventMapper implements AuditMapper<AuditEventPayload, Audit> {

    @Override
    public AuditEventPayload toObject(Audit audit) {
        return new AuditEventPayload()
                .setUsername(audit.getPublisher())
                .setCreatedAt(audit.getCreateAt())
                .setAction(audit.getAction())
                .setIdentifier(audit.getIdentifier());
    }

    @Override
    public Audit toAudit(AuditEventPayload auditEventPayload) {
        return new Audit()
                .setPublisher(auditEventPayload.getUsername())
                .setIdentifier(auditEventPayload.getIdentifier())
                .setAction(auditEventPayload.getAction())
                .setDetails(auditEventPayload.getDetails())
                // todo configure appClock bean and use it here
                .setReceivedAt(LocalDateTime.now(Clock.systemDefaultZone()))
                .setCreateAt(auditEventPayload.getCreatedAt());
    }

    @Override
    public List<AuditEventPayload> mapToObject(List<Audit> auditList) {
        return auditList.stream()
                .map(this::toObject)
                .toList();
    }

    @Override
    public List<Audit> mapToAudit(List<AuditEventPayload> auditEventPayloads) {
        return auditEventPayloads.stream()
                .map(this::toAudit)
                .toList();
    }
}
