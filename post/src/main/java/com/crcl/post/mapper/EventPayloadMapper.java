package com.crcl.post.mapper;

import com.crcl.core.dto.queue.payloads.CreatePostPayload;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.utils.FileUtils;
import com.crcl.post.dto.CreatePostRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {FileUtils.class}
)
public abstract class EventPayloadMapper {

    @Mappings(@Mapping(target = "files", ignore = true))
    public abstract CreatePostPayload toCreatePostPayload(CreatePostRequest request);

    public CreatePostPayload toCreatePostPayload(final CreatePostRequest createPostRequest,
                                                 final List<FileUploadResult> files) {
        CreatePostPayload createPostPayload = toCreatePostPayload(createPostRequest);
        createPostPayload.setFiles(files);
        return createPostPayload;
    }

}