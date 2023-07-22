package com.crcl.comment.service;

import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.dto.CommentFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService extends GenericService<CommentDto, Long> {
    CommentDto save(CommentFormDto commentFormDto);

    Page<CommentDto> findByPostId(String id, Pageable pageable);

    Integer countByPost(String postId);

}
