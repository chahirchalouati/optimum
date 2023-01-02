package com.crcl.post.mapper;

import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.post.domain.Attachment;
import com.crcl.post.dto.AttachmentDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AttachmentMapper extends GenericMapper<Attachment, AttachmentDto> {

}
