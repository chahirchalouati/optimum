package com.crcl.storage.service;

import com.crcl.storage.configuration.properties.ImageSizesProperties;
import com.crcl.storage.domain.FileRecord;
import com.crcl.storage.dto.ResizeImageRequest;
import com.crcl.storage.repository.RecordRepository;
import io.minio.MinioClient;
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

    @Override
    public void resize(ResizeImageRequest request) {
        final FileRecord record = request.getFileRecord();
        storageService.getResource(record)
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .subscribe(resource -> {
                    for (ImageSizesProperties.ImageSize imageSize : imageSizesProperties.getSizes().values()) {
                        try {
                            log.debug("Processing image with size {}", imageSize);
                            final var newFileName = getNewFileName(record.getName(), imageSize);
                            final var inputStream = getResizedImageInputStream(resource.getInputStream(), imageSize, getFileExtension(record.getName()));
                            saveImageToMinio(record, newFileName, inputStream);
                            updateFileRecord(record, newFileName);
                            log.debug("Finished processing image with size {}", imageSize);
                        } catch (Exception e) {
                            log.error("Failed to process image with size {}: {}", imageSize, e.getMessage(), e);
                        }
                    }
                    log.debug("Resized all image sizes for file record: {}", record);
                });
    }

    private String getNewFileName(String fileName, ImageSizesProperties.ImageSize imageSize) {
        String[] parts = fileName.split("\\.");
        String fileNameWithoutExtension = parts[parts.length - 2];
        String extension = getFileExtension(fileName);
        return fileNameWithoutExtension + imageSize.getLabel() + "." + extension;
    }

    private String getFileExtension(String fileName) {
        String[] parts = fileName.split("\\.");
        return parts[parts.length - 1];
    }

    private InputStream getResizedImageInputStream(InputStream input, ImageSizesProperties.ImageSize size, String extension) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(ImageIO.read(input))
                .outputFormat(extension)
                .size(size.getHeight(), size.getWidth())
                .toOutputStream(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private void saveImageToMinio(FileRecord fileRecord, String newFileName, InputStream inputStream) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(fileRecord.getBucket())
                .object(newFileName)
                .stream(inputStream, inputStream.available(), -1)
                .contentType(URLConnection.guessContentTypeFromName(fileRecord.getName()))
                .build();
        minioClient.putObject(args);
        log.debug("Saved resized image to MinIO with file name {}", newFileName);
    }

    private void updateFileRecord(FileRecord fileRecord, String newFileName) {
        FileRecord record = new FileRecord();
        BeanUtils.copyProperties(fileRecord, record, "id");
        record.setName(newFileName);
        fileRecord.getRecords().add(newFileName);
        recordRepository.save(fileRecord).block();
    }

}
