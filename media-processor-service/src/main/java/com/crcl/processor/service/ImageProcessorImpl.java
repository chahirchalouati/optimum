package com.crcl.processor.service;


import com.crcl.common.dto.DefaultMessage;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.processor.clients.StorageClient;
import com.crcl.processor.configuration.properties.ImageSizesProperties;
import com.crcl.processor.queue.MessageQueuePublisher;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageProcessorImpl implements ImageProcessor {

    private final UserService userService;
    private final StorageClient storageClient;
    private final MinioClient minioClient;
    private final ImageSizesProperties imageSizesProperties;
    private final MessageQueuePublisher messageQueuePublisher;

    @Override
    public void process(ImageUploadEvent event) {
        final FileUploadResult response = event.getResponse();

        storageClient.getObject(response.getName(), response.getEtag())
                .zipWith(userService.getCurrentUser())
                .subscribe(zipResult -> {
                            ByteArrayResource resource = zipResult.getT1();
                            final var userDto = zipResult.getT2();
                            for (ImageSize imageSize : imageSizesProperties.getSizes().values()) {
                                try {
                                    log.debug("Processing image with size {}", imageSize);
                                    final var newFileName = buildFileName(response.getName(), imageSize);
                                    final var inputStream = applySize(resource.getInputStream(), imageSize, getFileExtension(response.getName()));
                                    final var uploadFileResponse = uploadFile(userDto.getUsername(), newFileName, inputStream);
                                    final var message = new DefaultMessage<ImageUploadEvent>();

                                    final var imageUploadEvent = new ImageUploadEvent();
                                    imageUploadEvent.setImageSize(imageSize);
                                    imageUploadEvent.setResponse(buildFileUploadResponse(uploadFileResponse));
                                    imageUploadEvent.setId(response.getEtag());
                                    message.setPayload(imageUploadEvent);

                                    messageQueuePublisher.publish(message, QueueDefinition.UPDATE_ATTACHMENT_IMAGES_QUEUE);
                                    log.debug("Finished processing image with size {}", imageSize);
                                } catch (Exception e) {
                                    log.error("Failed to process image with size {}: {}", imageSize, e.getMessage(), e);
                                }
                            }
                            log.debug("Resized all image sizes for file record: {}", response);
                        }
                );
    }

    private FileUploadResult buildFileUploadResponse(ObjectWriteResponse uploadFileResponse) {
        return new FileUploadResult()
                .setBucket(uploadFileResponse.bucket())
                .setName(uploadFileResponse.object())
                .setEtag(uploadFileResponse.etag())
                .setContentType(URLConnection.guessContentTypeFromName(uploadFileResponse.object()))
                .setVersion(uploadFileResponse.versionId());
    }

    private String buildFileName(String fileName, ImageSize imageSize) {
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

    private InputStream applySize(InputStream input, ImageSize size, String extension) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(ImageIO.read(input))
                .outputFormat(extension)
                .size(size.getHeight(), size.getWidth())
                .toOutputStream(outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private ObjectWriteResponse uploadFile(String userBucket, String newFileName, InputStream inputStream) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(userBucket)
                .object(newFileName)
                .stream(inputStream, inputStream.available(), -1)
                .contentType(URLConnection.guessContentTypeFromName(newFileName))
                .build();
        log.debug("Saved resized image to MinIO with file name {}", newFileName);

        return minioClient.putObject(args);

    }

}
