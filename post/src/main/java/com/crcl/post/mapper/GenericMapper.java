package com.crcl.post.mapper;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.post.domain.GenericFile;
import com.crcl.post.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
@Slf4j
@RequiredArgsConstructor
public class GenericMapper implements FileMapper {

    @Override
    public Set<GenericFile> map(List<FileUploadResult> files, Post post) {
        // TODO: 15/09/23 Implement logic to handle generic files whose extensions are not included in the ALLOWED_EXTENSIONS

        return Set.of();
    }

    @Override
    public Predicate<FileUploadResult> filterByTypes(Set<String> types) {
        return result -> true;
    }
}
