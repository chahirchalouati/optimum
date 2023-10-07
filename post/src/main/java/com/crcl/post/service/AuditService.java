package com.crcl.post.service;

import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;

public interface AuditService {

    void auditPostCreated(final CreatePostRequest createPostRequest, final Post post);
}
