package com.crcl.audit.queue;

import com.crcl.audit.domain.Audit;
import com.crcl.audit.service.AuditService;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.queue.events.QEvent;
import com.crcl.core.dto.requests.AuditRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditQueueProcessorImpl implements AuditQueueProcessor {

    private final AuditService auditService;
    private final PayloadEventMapper eventMapper;

    @Override
    public void process(DefaultQEvent<AuditRequest> event) {
        Audit audit = eventMapper.toAudit(event.getPayload());
        auditService.save(audit);
    }

}
