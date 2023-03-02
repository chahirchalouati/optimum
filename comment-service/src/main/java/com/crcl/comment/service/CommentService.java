package com.crcl.comment.service;

import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.dto.CommentFormDto;

public interface CommentService extends GenericService<CommentDto, Long> {
    CommentDto save(CommentFormDto commentFormDto);
}
