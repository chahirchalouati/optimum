package com.crcl.post.service;

import com.crcl.core.utils.generic.GenericService;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;
import com.crcl.post.dto.CreatePostRequest;

import java.util.Set;

public interface TagService extends GenericService<Tag, String> {
    void handleTags(CreatePostRequest request, Post post);

    void handleTaggedUsers(CreatePostRequest request, Post post);

    Set<Tag> findByEntityId(String id);
}
