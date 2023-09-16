package com.crcl.post.mapper;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.utils.FileContentTypes;
import com.crcl.post.domain.GenericFile;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Video;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class VideoMapper implements FileMapper {

    @Override
    public Set<GenericFile> map(List<FileUploadResult> files, Post post) {
        AtomicInteger atomicInteger = new AtomicInteger();
        return files.stream()
                .filter(this.filterByTypes(FileContentTypes.IMAGE_FORMATS))
                .map(result -> {
                    Video video = new Video(atomicInteger.getAndIncrement());
                    video.setParent(result.getBucket())
                            .setUrl(result.getLink())
                            .setContentType(result.getContentType());
                    return video;
                })
                .peek(post::addVideo)
                .collect(Collectors.toSet());
    }
}
