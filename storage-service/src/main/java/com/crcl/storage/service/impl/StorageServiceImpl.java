package com.crcl.storage.service.impl;

import com.crcl.storage.configuration.properties.MinioProperties;
import com.crcl.storage.dto.FileUploadResponse;
import com.crcl.storage.service.StorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    public static final String BUCKET_NAME = "storage";
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    @SneakyThrows
    public ByteArrayResource getResource(String objectName, String eTag) {
        final var getObjectArgs = GetObjectArgs.builder()
                .object(objectName)
                .matchETag(eTag)
                .bucket(BUCKET_NAME)
                .build();

        return new ByteArrayResource(minioClient.getObject(getObjectArgs).readAllBytes());
    }

    @Override
    @SneakyThrows
    public FileUploadResponse save(MultipartFile multipartFile) {
        final var inputStream = multipartFile.getInputStream();
        final var params = getOwner()
                .map(owner -> Map.of(owner.getFirst(), owner.getSecond()))
                .orElse(Map.of());
        final var putObjectArgs = PutObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .extraQueryParams(params)
                .object(multipartFile.getOriginalFilename())
                .stream(inputStream, multipartFile.getSize(), -1)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .build();
        final var response = minioClient.putObject(putObjectArgs);

        return FileUploadResponse.builder()
                .contentType(multipartFile.getContentType())
                .bucket(BUCKET_NAME)
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

    private Optional<Pair<String, String>> getOwner() {
        final var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication instanceof JwtAuthenticationToken authenticationToken) {
            String username = authenticationToken.getToken().getClaim("username");
            return Optional.of(Pair.of("owner", username));
        }
        return Optional.empty();
    }
}
