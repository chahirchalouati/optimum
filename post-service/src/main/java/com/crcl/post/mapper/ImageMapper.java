package com.crcl.post.mapper;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.utils.FileContentTypes;
import com.crcl.post.domain.GenericFile;
import com.crcl.post.domain.Image;
import com.crcl.post.domain.Post;
import com.crcl.post.queue.PostQueuePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ImageMapper implements FileMapper {

    private final PostQueuePublisher postQueuePublisher;

    @Override
    public Set<GenericFile> map(List<FileUploadResult> files, Post post) {
        final var atomicInteger = new AtomicInteger(0);

        return files.stream()
                .filter(this.filterByTypes(FileContentTypes.IMAGE_FORMATS))
                .map(doProcessImage(atomicInteger, post))
                .collect(Collectors.toSet());
    }

    private Function<FileUploadResult, Image> doProcessImage(final AtomicInteger atomicInteger, @Nullable final Post post) {
        Assert.notNull(post, "The post object cannot be null for image processing. Ensure that a valid post object is provided.");

        return result -> {
            final var image = new Image(atomicInteger.getAndIncrement())
                    .setId(result.getEtag())
                    .setParent(result.getBucket())
                    .setUrl(result.getLink())
                    .setContentType(result.getContentType());
            post.addImage(image);

            final var processableImage = new ProcessableImage()
                    .setId(image.getId())
                    .setResult(result);
            postQueuePublisher.publishProcessableImageEvent(processableImage);

            return image;
        };
    }


}
