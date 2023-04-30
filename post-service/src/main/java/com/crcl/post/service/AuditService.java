package com.crcl.post.service;

import com.crcl.post.dto.PostDto;

public interface AuditService {

    void auditPostCreated(PostDto post);
}
