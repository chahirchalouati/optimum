package com.crcl.post.service;

import com.crcl.post.domain.Post;

public interface AuditService {
    void publishCreatedPostEvent(Post post);
}
