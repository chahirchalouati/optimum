package com.crcl.post.synchronizers;

import com.crcl.common.dto.queue.QEvent;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import com.crcl.common.dto.queue.ImageUpload;
import com.crcl.post.domain.Image;
import com.crcl.post.domain.Post;
import com.crcl.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageUploadSynchronizer implements Synchronizer<ImageUpload> {
    private final PostRepository postRepository;

    @NotNull
    private static Predicate<Image> filterImageById(String imageId) {
        return image -> image.getId().equals(imageId);
    }

    @Override
    public void synchronize(QEvent<ImageUpload> event) {
        var imageId = event.getPayload().getId();
        var fileUploadResult = event.getPayload().getResult();
        var imageSize = event.getPayload().getSize();

        Optional<Post> optionalPost = postRepository.findByImageId(imageId);
        optionalPost.ifPresent(
                storedPost -> storedPost.getImages().stream()
                        .filter(filterImageById(imageId))
                        .findFirst()
                        .ifPresentOrElse(addResizedImage(fileUploadResult, imageSize, optionalPost), () -> log.info("no image found for id %s".formatted(imageId)))
        );
    }

    @NotNull
    private Consumer<Image> addResizedImage(FileUploadResult fileUploadResult, ImageSize imageSize, Optional<Post> postOptional) {
        return image -> {
            var imageToStore = new Image()
                    .setImageSize(imageSize)
                    .setParent(fileUploadResult.getEtag())
                    .setContentType(fileUploadResult.getContentType())
                    .setUrl(fileUploadResult.getLink());
            image.getProcessedImages().add(imageToStore);
            postRepository.save(postOptional.get());
        };
    }
}
