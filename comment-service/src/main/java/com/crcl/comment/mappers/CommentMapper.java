package com.crcl.comment.mappers;

import com.crcl.comment.domain.Comment;
import com.crcl.comment.dto.CommentDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper extends GenericMapper<Comment, CommentDto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentDto commentDto, @MappingTarget Comment comment);

}