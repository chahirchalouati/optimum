package com.crcl.audit.queue;

import com.crcl.common.dto.queue.events.DefaultQEvent;
import com.crcl.common.dto.requests.AuditRequest;
import com.crcl.common.utils.QueueDefinition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuditQueueConsumer {

    private final AuditQueueProcessor queueProcessor;

    @RabbitListener(queues = QueueDefinition.AUDIT_MESSAGE_QUEUE)
    public void consumeAuditEvent(DefaultQEvent<AuditRequest> message) {
        queueProcessor.process(message);
    }
}
