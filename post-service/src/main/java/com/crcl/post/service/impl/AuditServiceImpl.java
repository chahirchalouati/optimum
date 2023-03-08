package com.crcl.post.service.impl;

import com.crcl.common.dto.DefaultQEvent;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.AuditRequest;
import com.crcl.post.mapper.PostAuditMapper;
import com.crcl.post.queue.EventQueuePublisher;
import com.crcl.post.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final EventQueuePublisher queuePublisher;
    private final PostAuditMapper postAuditMapper;

    @Override
    public void publishCreatedPostEvent(Post post) {
        AuditRequest audit = postAuditMapper.toAudit(post);
        final var event = new DefaultQEvent<AuditRequest>()
                .setPayload(audit);
        queuePublisher.sendMessage(event, QueueDefinition.AUDIT_MESSAGE_QUEUE);
    }
}
