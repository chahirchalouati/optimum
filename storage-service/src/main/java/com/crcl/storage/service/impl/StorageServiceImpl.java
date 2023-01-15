package com.crcl.storage.service.impl;

import com.crcl.storage.domain.FileRecord;
import com.crcl.storage.dto.FileUploadResponse;
import com.crcl.storage.exceptions.CreateRecordException;
import com.crcl.storage.exceptions.NotFoundException;
import com.crcl.storage.repository.RecordRepository;
import com.crcl.storage.service.StorageService;
import io.minio.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final MinioClient minioClient;
    private final RecordRepository recordRepository;
    private final BucketsResolver bucketsResolver;

    private Function<ObjectWriteResponse, FileRecord> saveFileRecord() {
        return response -> new FileRecord()
                .setTag(response.etag())
                .setName(response.object())
                .setTag(response.etag())
                .setVersion(response.versionId());
    }

    @Override
    public Flux<FileUploadResponse> saveAll(Flux<FilePart> filePartFlux) {
        return filePartFlux.flatMap(filePart -> this.save(Mono.just(filePart)));
    }

    @Override
    @SneakyThrows(Exception.class)
    public Mono<FileUploadResponse> save(Mono<FilePart> particle) {
        return convert(particle)
                .zipWith(particle)
                .map(
                        mapToStoreRequest()
                                .andThen(storeFile())
                                .andThen(saveFileRecord())
                )
                .flatMap(recordRepository::save)
                .map(mapToUploadResponse())
                .switchIfEmpty(Mono.error(CreateRecordException::new));
    }

    @Override
    @SneakyThrows
    public Mono<ByteArrayResource> getResource(String objectName, String eTag) {
        final var getObjectArgs = GetObjectArgs.builder()
                .object(objectName)
                .matchETag(eTag)
                .bucket(bucketsResolver.resolve())
                .build();

        return Mono.just(minioClient.getObject(getObjectArgs))
                .map(this::getAllBytes)
                .map(ByteArrayResource::new)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private Function<Tuple2<InputStream, FilePart>, PutObjectArgs> mapToStoreRequest() {
        return zip -> {
            final var inputStream = zip.getT1();
            final var filename = zip.getT2().filename();
            int available = 0;
            try {
                available = inputStream.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final var type = Objects.requireNonNull(zip.getT2().headers().getContentType()).getType();

            return PutObjectArgs.builder()
                    .extraHeaders(zip.getT2().headers().toSingleValueMap())
                    .bucket(bucketsResolver.resolve())
                    .object(filename)
                    .stream(inputStream, available, -1)
                    .contentType(type)
                    .build();
        };
    }


    @NotNull
    private Function<PutObjectArgs, ObjectWriteResponse> storeFile() {
        return args -> {
            try {
                return minioClient.putObject(args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public Mono<InputStream> convert(Mono<FilePart> particle) {
        return particle.flatMapMany(Part::content)
                .reduce(DataBuffer::write)
                .map(dataBuffer -> new ByteArrayInputStream(dataBuffer.asByteBuffer().array()));
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
