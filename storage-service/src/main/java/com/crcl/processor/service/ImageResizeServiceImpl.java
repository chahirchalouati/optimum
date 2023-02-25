package com.crcl.processor.service;

import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.processor.configuration.properties.ImageSizesProperties;
import com.crcl.processor.domain.FileRecord;
import com.crcl.processor.dto.ResizeImageRequest;
import com.crcl.processor.queue.ResizeImageQueueSender;
import com.crcl.processor.repository.RecordRepository;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageResizeServiceImpl implements ImageResizeService {

    private final StorageService storageService;
    private final MinioClient minioClient;
    private final ImageSizesProperties imageSizesProperties;
    private final RecordRepository recordRepository;
    private final ResizeImageQueueSender resizeImageQueueSender;

    @Override
    public void resize(ResizeImageRequest request) {
        final FileRecord record = request.getFileRecord();
        storageService.getResource(record)
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .subscribe(resource -> {
                    for (ImageSize imageSize : imageSizesProperties.getSizes().values()) {
                        try {
                            log.debug("Processing image with size {}", imageSize);
                            var newFileName = buildFileName(record.getName(), imageSize);
                            var inputStream = getResizedImageInputStream(resource.getInputStream(), imageSize, getFileExtension(record.getName()));
                            var response = saveImageToMinio(record, newFileName, inputStream);
                            updateFileRecord(record, newFileName);
                            record.setTag(response.etag());
                            resizeImageQueueSender.updateImageAttachment(buildMessage(record, newFileName, imageSize));
                            log.debug("Finished processing image with size {}", imageSize);
                        } catch (Exception e) {
                            log.error("Failed to process image with size {}: {}", imageSize, e.getMessage(), e);
                        }
                    }
                    log.debug("Resized all image sizes for file record: {}", record);
                });
    }

    private ImageUploadEvent buildMessage(FileRecord record, String name, ImageSize imageSize) {
        FileUploadResult response = new FileUploadResult()
                .setContentType(record.getType())
                .setBucket(record.getBucket())
                .setEtag(record.getTag())
                .setName(name)
                .setVersion(record.getVersion());
        ImageUploadEvent imageUploadEvent = new ImageUploadEvent();
        imageUploadEvent.setImageSize(imageSize);
        imageUploadEvent.setResponse(response);
        return imageUploadEvent;

    }

    private String buildFileName(String fileName, ImageSize imageSize) {
        String[] parts = fileName.split("\\.");
        String fileNameWithoutExtension = parts[parts.length - 2];
        String extension = getFileExtension(fileName);
        return fileNameWithoutExtension + imageSize.getLabel() + "." + extension;
    }

    private String getFileExtension(String fileName) {
        String[] parts = fileName.split("\\.");
        return parts[parts.length - 1];
    }

    private InputStream getResizedImageInputStream(InputStream input, ImageSize size, String extension) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(ImageIO.read(input))
                .outputFormat(extension)
                .size(size.getHeight(), size.getWidth())
                .toOutputStream(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private ObjectWriteResponse saveImageToMinio(FileRecord fileRecord, String newFileName, InputStream inputStream) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(fileRecord.getBucket())
                .object(newFileName)
                .stream(inputStream, inputStream.available(), -1)
                .contentType(URLConnection.guessContentTypeFromName(fileRecord.getName()))
                .build();
        log.debug("Saved resized image to MinIO with file name {}", newFileName);
        return minioClient.putObject(args);

    }

    private void updateFileRecord(FileRecord fileRecord, String newFileName) {
        FileRecord record = new FileRecord();
        BeanUtils.copyProperties(fileRecord, record, "id");
        record.setName(newFileName);
        fileRecord.getRecords().add(newFileName);
        recordRepository.save(fileRecord).block();
    }

}
