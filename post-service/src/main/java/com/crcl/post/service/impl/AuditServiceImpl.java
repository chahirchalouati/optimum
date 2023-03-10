package com.crcl.post.service.impl;

import com.crcl.common.dto.DefaultQEvent;
import com.crcl.common.dto.requests.AuditRequest;
import com.crcl.common.utils.AuditAction;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.domain.Post;
import com.crcl.post.queue.EventQueuePublisher;
import com.crcl.post.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final EventQueuePublisher queuePublisher;

    @Override
    public void publishCreatedPostEvent(Post post) {
        AuditRequest audit = toAuditRequest(post);
        final var event = new DefaultQEvent<AuditRequest>()
                .setPayload(audit);
        queuePublisher.sendMessage(event, QueueDefinition.AUDIT_MESSAGE_QUEUE);
    }

    private AuditRequest toAuditRequest(Post post) {
        return new AuditRequest()
                .setAction(AuditAction.CREATE_POST_ACTION)
                .setIdentifier(String.valueOf(post.getId()))
                .setDetails(Map.of("post", post));
    }
}
