package com.crcl.post.service;

import com.crcl.core.utils.generic.GenericService;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Share;
import com.crcl.post.dto.CreatePostRequest;

public interface ShareService extends GenericService<Share, String> {
    void handleShares(CreatePostRequest request, Post post);
}
