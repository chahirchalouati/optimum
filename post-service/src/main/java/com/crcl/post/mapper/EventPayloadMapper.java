package com.crcl.post.mapper;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.utils.FileUtils;
import com.crcl.post.client.StorageClient;
import com.crcl.post.dto.CreatePostRequest;
import lombok.SneakyThrows;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {FileUtils.class, StorageClient.class}
)
public abstract class EventPayloadMapper {
    @Autowired
    StorageClient storageClient;

    @Mappings(
            @Mapping(target = "files", expression = "java(requestToFile(request))")
    )
    public abstract CreatePostPayload toCreatePostPayload(CreatePostRequest request);

    @SneakyThrows(Exception.class)
    protected List<FileUploadResult> requestToFile(CreatePostRequest request) {

        if (!request.getFiles().isEmpty()) {
            return this.storageClient.saveAll(request.getFiles());
        }

        return Collections.emptyList();
    }
}