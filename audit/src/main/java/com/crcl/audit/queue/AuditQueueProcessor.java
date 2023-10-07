package com.crcl.audit.queue;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.AuditEventPayload;

public interface AuditQueueProcessor {

    void process(DefaultQEvent<AuditEventPayload> event);
}
