package com.crcl.post.mapper;

import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.PostDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper extends GenericMapper<Post, PostDto> {
}
