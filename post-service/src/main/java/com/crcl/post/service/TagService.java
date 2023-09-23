package com.crcl.post.service;

import com.crcl.core.utils.generic.GenericService;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;

import java.util.List;
import java.util.Set;

public interface TagService extends GenericService<Tag, String> {
    void processTags(List<Tag> request, Post post);
    Set<Tag> findByEntityId(String id);
}
