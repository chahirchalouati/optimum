package com.crcl.post.service;

import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.context.SecurityContext;

public interface PostQueueService {
    @NotBlank
    void publishCreatePostEvent(final PostDto postDto);

    void publishReceiveCreatePostRequestEvent(CreatePostRequest createPostRequest, final Post request);

    void publishReceiveCreatePostRequestEvent(SecurityContext securityContext, CreatePostRequest request, Post post);
}
