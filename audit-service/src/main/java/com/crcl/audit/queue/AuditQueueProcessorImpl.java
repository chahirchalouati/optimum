package com.crcl.audit.queue;

import com.crcl.audit.repository.AuditRepository;
import com.crcl.common.dto.QEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditQueueProcessorImpl implements AuditQueueProcessor {
    private final AuditRepository auditRepository;

    @Override
    public <T> void process(QEvent<T> message) {
        log.info(message.getPayload().toString());
    }
}
