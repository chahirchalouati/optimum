package com.crcl.post.service.impl;

import com.crcl.post.dto.PostDto;
import com.crcl.post.service.AuditService;
import com.crcl.post.service.NotificationService;
import com.crcl.post.service.PostQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostQueueServiceImpl implements PostQueueService {

    private final NotificationService notificationService;
    private final AuditService auditService;

    @Override
    public void publishCreatePostEvent(PostDto postDto) {
        auditService.auditPostCreated(postDto);
        notificationService.notifyCreatedPost(postDto);

    }
}
