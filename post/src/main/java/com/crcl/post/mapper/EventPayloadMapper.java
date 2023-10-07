package com.crcl.post.mapper;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.utils.FileUtils;
import com.crcl.post.dto.CreatePostRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {FileUtils.class}
)
public abstract class EventPayloadMapper {

    @Mappings(@Mapping(target = "files", ignore = true))
    public abstract CreatePostPayload toCreatePostPayload(CreatePostRequest request);

}