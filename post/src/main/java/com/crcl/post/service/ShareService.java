package com.crcl.post.service;

import com.crcl.core.utils.generic.GenericService;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Share;

import java.util.List;

public interface ShareService extends GenericService<Share, String> {
    void handleShares(List<String> request, Post post);
}
