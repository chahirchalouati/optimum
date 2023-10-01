package com.crcl.post.mapper;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.post.domain.GenericFile;
import com.crcl.post.domain.Post;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public interface FileMapper {
    Set<GenericFile> map(List<FileUploadResult> files, Post post);

    default Predicate<FileUploadResult> filterByTypes(Set<String> types) {
        return result -> types.contains(result.getContentType());
    }
}
