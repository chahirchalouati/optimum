package com.crcl.processor.service.impl;


import com.crcl.core.domain.Orientation;
import com.crcl.core.dto.UserDto;
import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.properties.ImageSize;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.processor.clients.StorageClient;
import com.crcl.processor.configuration.properties.ImageSizesProperties;
import com.crcl.processor.queue.EventQueuePublisher;
import com.crcl.processor.service.ImageProcessor;
import com.crcl.processor.service.UserService;
import com.crcl.processor.utils.FileExtensionUtils;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageProcessorImpl implements ImageProcessor {

    private static final String FILE_TAG_KEY = "file_tag_key";

    private final UserService userService;
    private final StorageClient storageClient;
    private final MinioClient minioClient;
    private final ImageSizesProperties imageSizesProperties;
    private final EventQueuePublisher eventQueuePublisher;

    @Override
    public void process(AuthenticatedQEvent<CreatePostPayload> event) {
        Collection<ImageSize> sizes = imageSizesProperties.getSizes().values();
        event.getPayload().getFiles().stream()
                .filter(result -> FileExtensionUtils.isImage(result.getName()))
                .forEach(result -> {
                    log.debug("Resized all image sizes for file record: {}", result);

                    storageClient.getObject(result.getName(), result.getEtag())
                            .zipWith(userService.getCurrentUser())
                            .subscribe(zipResult -> sizes.forEach(doProcessImage(result,
                                    zipResult.getT1(),
                                    zipResult.getT2())));

                });

    }

    private Consumer<ImageSize> doProcessImage(FileUploadResult response, ByteArrayResource resource, UserDto userDto) {
        return imageSize -> {
            try {
                log.debug("Processing image with size {}", imageSize);
                final var newFileName = obtainNewFileName(response.getName(), imageSize);
                final var inputStream = resizeImage(resource.getInputStream(), imageSize, getFileExtension(response.getName()));
                final var uploadFileResponse = storeImage(userDto.getUsername(), newFileName, inputStream);
                final var orientation = getOrientation(inputStream);

                final var processableImage = new ProcessableImage()
                        .setSize(imageSize)
                        .setOrientation(orientation)
                        .setResult(buildFileUploadResponse(uploadFileResponse))
                        .setId(response.getEtag());

                final var event = new DefaultQEvent<ProcessableImage>()
                        .withPayload(processableImage);

                eventQueuePublisher.publishMessage(event, QueueDefinition.PUSH_PROCESSED_IMAGE_QUEUE);
                log.debug("Finished processing image with size {}", imageSize);
            } catch (Exception e) {
                log.error("Failed to process image with size {}: {}", imageSize, e.getMessage(), e);
            }
        };
    }

    private Orientation getOrientation(InputStream inputStream) {

        try {
            if (inputStream.markSupported()) {
                inputStream.reset();
            }

            BufferedImage image = ImageIO.read(inputStream);
            final int width = image.getWidth();
            final int height = image.getHeight();

            if (width > height) {
                return Orientation.LANDSCAPE;
            } else {
                return Orientation.PORTRAIT;
            }

        } catch (IOException e) {
            log.error("Failed to read image file: {} ", e.getMessage());
        }
        return Orientation.LANDSCAPE;
    }

    private FileUploadResult buildFileUploadResponse(ObjectWriteResponse uploadFileResponse) {
        return new FileUploadResult()
                .setBucket(uploadFileResponse.bucket())
                .setName(uploadFileResponse.object())
                .setEtag(uploadFileResponse.etag())
                .setContentType(URLConnection.guessContentTypeFromName(uploadFileResponse.object()))
                .setVersion(uploadFileResponse.versionId());
    }

    private String obtainNewFileName(String fileName, ImageSize imageSize) {
        String[] parts = fileName.split("\\.");
        String fileNameWithoutExtension = parts[parts.length - 2];
        String extension = getFileExtension(fileName);

        return fileNameWithoutExtension + imageSize.getLabel() + "." + extension;
    }

    private String getFileExtension(String fileName) {
        String[] parts = fileName.split("\\.");
        Assert.notNull(parts[parts.length - 1], "File extension cannot be null for file name: " + fileName);

        return parts[parts.length - 1];
    }

    private InputStream resizeImage(InputStream input, ImageSize size, String extension) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(ImageIO.read(input))
                .outputFormat(extension)
                .size(size.getHeight(), size.getWidth())
                .toOutputStream(outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private ObjectWriteResponse storeImage(String userBucket, String newFileName, InputStream inputStream) throws Exception {
        String timestamp = Long.toString(System.currentTimeMillis());
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(userBucket)
                .tags(Collections.singletonMap(FILE_TAG_KEY, timestamp + "_" + UUID.randomUUID()))
                .object(newFileName)
                .stream(inputStream, inputStream.available(), -1)
                .contentType(URLConnection.guessContentTypeFromName(newFileName))
                .build();
        log.debug("Saved resized image to MinIO with file name {}", newFileName);

        return minioClient.putObject(args);
    }

}
