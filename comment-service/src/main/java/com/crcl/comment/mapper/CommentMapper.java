package com.crcl.comment.mapper;

import com.crcl.comment.domain.Comment;
import com.crcl.comment.dto.CommentDto;
import com.crcl.core.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AttachmentMapper.class}
)
public interface CommentMapper extends GenericMapper<Comment, CommentDto> {
}
