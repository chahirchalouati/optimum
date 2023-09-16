package com.crcl.audit.queue;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.queue.events.QEvent;
import com.crcl.core.dto.requests.AuditRequest;

public interface AuditQueueProcessor {

    void process(DefaultQEvent<AuditRequest> event);
}
