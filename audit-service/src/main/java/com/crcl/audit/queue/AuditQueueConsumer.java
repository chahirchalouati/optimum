package com.crcl.audit.queue;

import com.crcl.common.dto.QEvent;
import com.crcl.common.utils.QueueDefinition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuditQueueConsumer {

    private final AuditQueueProcessor queueProcessor;

    @RabbitListener(queues = QueueDefinition.AUDIT_MESSAGE_QUEUE)
    public void consumeImageUploadEvent(Message<QEvent> message) {
        queueProcessor.<QEvent>process(message.getPayload());
    }


}
