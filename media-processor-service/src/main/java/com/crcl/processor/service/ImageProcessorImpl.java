package com.crcl.processor.service;


import com.crcl.common.domain.Orientation;
import com.crcl.common.dto.AuthenticatedQEvent;
import com.crcl.common.dto.DefaultQEvent;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import com.crcl.common.queue.ImageUpload;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.processor.clients.StorageClient;
import com.crcl.processor.configuration.properties.ImageSizesProperties;
import com.crcl.processor.queue.EventQueuePublisher;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageProcessorImpl implements ImageProcessor {

    private final UserService userService;
    private final StorageClient storageClient;
    private final MinioClient minioClient;
    private final ImageSizesProperties imageSizesProperties;
    private final EventQueuePublisher eventQueuePublisher;

    @Override
    public void process(AuthenticatedQEvent<ImageUpload> event) {
        final FileUploadResult response = event.getPayload().getResponse();

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
                                    final var orientation = getOrientation(inputStream);

                                    final var imageUploadEvent = new ImageUpload();
                                    imageUploadEvent.setImageSize(imageSize);
                                    imageUploadEvent.setOrientation(orientation);
                                    imageUploadEvent.setResponse(buildFileUploadResponse(uploadFileResponse));
                                    imageUploadEvent.setId(response.getEtag());

                                    final var message = new DefaultQEvent<ImageUpload>();
                                    message.setPayload(imageUploadEvent);

                                    eventQueuePublisher.publish(message, QueueDefinition.UPDATE_ATTACHMENT_IMAGES_QUEUE);
                                    log.debug("Finished processing image with size {}", imageSize);
                                } catch (Exception e) {
                                    log.error("Failed to process image with size {}: {}", imageSize, e.getMessage(), e);
                                }
                            }
                            log.debug("Resized all image sizes for file record: {}", response);
                        }
                );
    }


    private Orientation getOrientation(InputStream inputStream) {

        try {
            if (inputStream.markSupported()) {
                inputStream.reset();
            }
            BufferedImage image = ImageIO.read(inputStream);
            int width = image.getWidth();
            int height = image.getHeight();
            if (width > height) {
                return Orientation.LANDSCAPE;
            } else {
                return Orientation.PORTRAIT;
            }
        } catch (IOException e) {
            System.out.println("Failed to read image file: " + e.getMessage());
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
