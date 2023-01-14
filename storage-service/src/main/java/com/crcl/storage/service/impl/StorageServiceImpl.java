package com.crcl.storage.service.impl;

import com.crcl.storage.configuration.properties.MinioProperties;
import com.crcl.storage.domain.FileRecord;
import com.crcl.storage.dto.FileUploadResponse;
import com.crcl.storage.exceptions.CreateRecordException;
import com.crcl.storage.exceptions.NotFoundException;
import com.crcl.storage.repository.RecordRepository;
import com.crcl.storage.service.StorageService;
import io.minio.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        return response -> {
            Headers headers = response.headers();
            System.out.println(headers);
            return new FileRecord()
                    .setTag(response.etag())
                    .setName(response.object())
                    .setTag(response.etag())
                    .setVersion(response.versionId());
        };
    }

    @NotNull
    private static Function<Tuple2<InputStream, FilePart>, PutObjectArgs> mapFileToArgs() {
        return zip -> {
            var inputStream = zip.getT1();
            var filename = zip.getT2().filename();
            int available = 0;
            try {
                available = inputStream.available();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String type = zip.getT2().headers().getContentType().getType();
            return PutObjectArgs.builder()
                    .extraHeaders(zip.getT2().headers().toSingleValueMap())
                    .bucket(BUCKET_NAME)
                    .object(filename)
                    .stream(inputStream, available, -1)
                    .contentType(type != null ? type : MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .build();
        };
    }

    @Override
    @SneakyThrows
    public Mono<ByteArrayResource> getResource(String objectName, String eTag) {
        final var getObjectArgs = GetObjectArgs.builder()
                .object(objectName)
                .matchETag(eTag)
                .bucket(BUCKET_NAME)
                .build();

        return Mono.just(minioClient.getObject(getObjectArgs))
                .map(this::getAllBytes)
                .map(ByteArrayResource::new)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    @Override
    @SneakyThrows(Exception.class)
    public Mono<FileUploadResponse> save(Mono<FilePart> particle) {
        return convert(particle)
                .zipWith(particle)
                .map(mapFileToArgs())
                .map(args -> {
                    try {
                        return minioClient.putObject(args);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(mapToFile())
                .map(mapToUploadResponse())
                .switchIfEmpty(Mono.error(CreateRecordException::new));
    }

    public Mono<InputStream> convert(Mono<FilePart> particle) {
        return particle.flatMapMany(Part::content)
                .reduce(DataBuffer::write)
                .map(dataBuffer -> new ByteArrayInputStream(dataBuffer.asByteBuffer().array()));
    }

    @Override
    public Flux<FileUploadResponse> saveAll(Flux<FilePart> filePartFlux) {
        return filePartFlux.flatMap(filePart -> this.save(Mono.just(filePart)));
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

    private Function<FileRecord, FileUploadResponse> mapToUploadResponse() {
        return file -> FileUploadResponse.builder()
                .contentType(file.getType())
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
