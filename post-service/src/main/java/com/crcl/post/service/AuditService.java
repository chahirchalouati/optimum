package com.crcl.post.service;

import com.crcl.common.dto.requests.AuditRequest;
import com.crcl.post.dto.PostDto;

public interface AuditService {

    void auditPostCreated(PostDto post);
}
