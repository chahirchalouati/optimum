package com.crcl.comment.service;

import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.dto.CommentFormDto;
import com.crcl.common.dto.EntityCountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CommentService extends GenericService<CommentDto, Long> {
    CommentDto save(CommentFormDto commentFormDto);

    Page<CommentDto> findByPostId(String id, Pageable pageable);

    Integer countByPost(String postId);

    Map<String, Long> countByPosts(List<String> postIds);
}
