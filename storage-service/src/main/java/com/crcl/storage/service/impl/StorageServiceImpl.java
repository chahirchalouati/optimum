package com.crcl.storage.service.impl;

import com.crcl.storage.configuration.MinioProps;
import com.crcl.storage.dto.FileUploadResponse;
import com.crcl.storage.service.StorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final MinioClient minioClient;
    private final MinioProps minioProps;

    @Override
    @SneakyThrows
    public ByteArrayResource getResource(String objectName, String eTag) {
        final var getObjectArgs = GetObjectArgs.builder()
                .object(objectName)
                .matchETag(eTag)
                .bucket(minioProps.getDefaultBucketName())
                .build();

        return new ByteArrayResource(minioClient.getObject(getObjectArgs).readAllBytes());
    }

    @Override

    @SneakyThrows
    public FileUploadResponse save(MultipartFile multipartFile) {
        final var inputStream = multipartFile.getInputStream();
        final var putObjectArgs = PutObjectArgs.builder()
                .bucket(minioProps.getDefaultBucketName())
                .object(multipartFile.getOriginalFilename())
                .stream(inputStream, multipartFile.getSize(), -1)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .build();
        final var response = minioClient.putObject(putObjectArgs);

        return FileUploadResponse.builder()
                .bucket(minioProps.getDefaultBucketName())
                .etag(response.etag())
                .name(response.object())
                .version(response.versionId())
                .build();
    }

    @Override
    public List<FileUploadResponse> saveAll(Collection<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                .map(this::save)
                .toList();
    }

}
