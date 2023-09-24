package com.crcl.post.mapper;

import com.crcl.core.dto.File;
import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.post.dto.CreatePostRequest;
import lombok.SneakyThrows;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class EventPayloadMapper {
    @Mappings(
            @Mapping(target = "files", expression = "java(requestToFile(request))")
    )
    public abstract CreatePostPayload toCreatePostPayload(CreatePostRequest request);

    @SneakyThrows(Exception.class)
    protected List<File> requestToFile(CreatePostRequest request) {
        return request.getFiles().stream()
                .map(file -> {
                    try {
                        return new File(file.getOriginalFilename(), file.getContentType(), file.getInputStream().readAllBytes());
                    } catch (IOException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }
}