package com.crcl.storage.service.impl;

import com.crcl.storage.configuration.properties.MinioProperties;
import com.crcl.storage.domain.FileRecord;
import com.crcl.storage.dto.FileUploadResponse;
import com.crcl.storage.exceptions.NotFoundException;
import com.crcl.storage.repository.RecordRepository;
import com.crcl.storage.service.StorageService;
import io.minio.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    public static final String BUCKET_NAME = "storage";
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    private final RecordRepository recordRepository;

    private static Function<ObjectWriteResponse, FileRecord> mapToFile() {
        return response -> new FileRecord()
                .setTag(response.etag())
                .setName(response.object())
                .setTag(response.etag())
                .setVersion(response.versionId());
    }

    @Override
    @SneakyThrows
    public Mono<ByteArrayResource> getResource(String objectName, String eTag) {
        final var getObjectArgs = GetObjectArgs.builder()
                .object(objectName)
                .matchETag(eTag)
                .bucket(BUCKET_NAME)
                .build();

        return Mono.justOrEmpty(minioClient.getObject(getObjectArgs))
                .onErrorResume(throwable -> Mono.error(new NotFoundException(throwable.getMessage())))
                .switchIfEmpty(Mono.error(NotFoundException::new))
                .map(this::getAllBytes)
                .map(ByteArrayResource::new);
    }

    @Override
    @SneakyThrows
    public Mono<FileUploadResponse> save(MultipartFile multipartFile) {
        final var inputStream = multipartFile.getInputStream();
        final var putObjectArgs = PutObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object(multipartFile.getOriginalFilename())
                .stream(inputStream, multipartFile.getSize(), -1)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .build();

        return Mono.justOrEmpty(minioClient.putObject(putObjectArgs))
                .map(mapToFile())
                .map(mapToUploadResponse(multipartFile.getContentType()));
    }

    @Override
    public Flux<FileUploadResponse> saveAll(Collection<MultipartFile> multipartFiles) {
        return Flux.fromIterable(multipartFiles)
                .flatMap(this::save);
    }

    private Optional<Pair<String, String>> getOwner() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken authenticationToken) {
            final var token = authenticationToken.getToken();
            final var username = (String) Optional.ofNullable(token.getClaim("username"))
                    .orElse(token.getClaim("sub"));
            return Optional.of(Pair.of("owner", username));
        }
        return Optional.empty();
    }

    private Function<FileRecord, FileUploadResponse> mapToUploadResponse(String contentType) {
        return file -> FileUploadResponse.builder()
                .contentType(contentType)
                .bucket(file.getBucket())
                .etag(file.getTag())
                .name(file.getName())
                .version(file.getVersion())
                .build();
    }

    private byte[] getAllBytes(GetObjectResponse getObjectResponse) {
        try {
            return getObjectResponse.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
