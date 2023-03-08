package com.crcl.audit.queue;

import com.crcl.common.dto.QEvent;

public interface AuditQueueProcessor {

    <T> void process(QEvent<T> message);
}
