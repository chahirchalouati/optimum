package com.crcl.post.service;

import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import org.springframework.security.core.context.SecurityContext;

public interface PostProcessor {

    void processPostAsync(SecurityContext securityContext, CreatePostRequest request, Post post);
}
