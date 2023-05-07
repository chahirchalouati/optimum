package com.crcl.processor.service.impl;

import com.crcl.common.dto.queue.ImageUpload;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.processor.domain.FileRecord;
import com.crcl.processor.dto.WriteResponse;
import com.crcl.processor.exceptions.CreateRecordException;
import com.crcl.processor.exceptions.NotFoundException;
import com.crcl.processor.queue.ResizeImageQueuePublisher;
import com.crcl.processor.repository.RecordRepository;
import com.crcl.processor.service.StorageService;
import com.crcl.processor.utils.FileExtensionUtils;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
import java.net.URLConnection;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {

    private static final String FILE_TAG_KEY = "file_tag_key";
    private final MinioClient minioClient;
    private final RecordRepository recordRepository;
    private final BucketsResolver bucketsResolver;
    private final ResizeImageQueuePublisher imageQueuePublisher;

    @Override
    public Flux<FileUploadResult> saveAll(Flux<FilePart> filePartFlux) {
        return filePartFlux.flatMap(filePart -> this.save(Mono.just(filePart)));
    }

    @Override
    @SneakyThrows(Exception.class)
    public Mono<FileUploadResult> save(Mono<FilePart> particle) {

        var inputStreamResult = toInputStream(particle)
                .zipWith(particle);

        var doUpload = createMinioRequest()
                .andThen(uploadFile())
                .andThen(buildFileRecord());

        return inputStreamResult.map(doUpload)
                .flatMap(recordRepository::save)
                .doOnNext(publishImageUploadEvent())
                .map(createFileUploadResponse())
                .switchIfEmpty(Mono.error(CreateRecordException::new));
    }

    @Override
    @SneakyThrows
    public Mono<ByteArrayResource> getResource(String fileName, String eTag, String bucket) {
        var getObjectArgs = GetObjectArgs.builder()
                .object(fileName)
                .extraQueryParams(Collections.singletonMap("tags", eTag))
                .bucket(bucket)
                .build();

        return Mono.just(minioClient.getObject(getObjectArgs))
                .map(this::getAllBytes)
                .map(ByteArrayResource::new)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    @Override
    public Mono<ByteArrayResource> getResource(FileRecord record) {
        return this.getResource(record.getName(), record.getOwner(), record.getBucket());
    }

    private Function<Tuple2<InputStream, FilePart>, PutObjectArgs> createMinioRequest() {
        return zip -> {
            try {
                var inputStream = zip.getT1();
                var filename = zip.getT2().filename();
                int available = inputStream.available();

                String timestamp = Long.toString(System.currentTimeMillis());
                return PutObjectArgs.builder()
                        .extraHeaders(zip.getT2().headers().toSingleValueMap())
                        .bucket(bucketsResolver.resolve())
                        .object(filename)
                        .tags(Collections.singletonMap(FILE_TAG_KEY, timestamp + "_" + UUID.randomUUID()))
                        .stream(inputStream, available, -1)
                        .contentType(URLConnection.guessContentTypeFromName(filename))
                        .build();
            } catch (IOException e) {
                log.error("Failed to create Minio request: {}", e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    private Mono<InputStream> toInputStream(Mono<FilePart> particle) {
        return particle.flatMapMany(Part::content)
                .reduce(DataBuffer::write)
                .map(dataBuffer -> new ByteArrayInputStream(dataBuffer.asByteBuffer().array()));
    }

    private Function<PutObjectArgs, WriteResponse> uploadFile() {
        return args -> {
            try {
                ObjectWriteResponse objectWriteResponse = minioClient.putObject(args);
                return new WriteResponse(objectWriteResponse, args.tags().get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    private Function<FileRecord, FileUploadResult> createFileUploadResponse() {
        return record -> new FileUploadResult()
                .setContentType(record.getType())
                .setBucket(record.getBucket())
                .setEtag(record.getTag())
                .setName(record.getName())
                .setVersion(record.getVersion());
    }

    private byte[] getAllBytes(GetObjectResponse getObjectResponse) {
        try {
            return getObjectResponse.readAllBytes();
        } catch (IOException e) {
            log.error("Failed to read all bytes from Minio object: {}", e.getMessage(), e);
            return new byte[0];
        }
    }

    private Function<WriteResponse, FileRecord> buildFileRecord() {
        return response -> {
            ObjectWriteResponse writeResponse = response.getWriteResponse();
            return new FileRecord()
                    .setType(URLConnection.guessContentTypeFromName(writeResponse.object()))
                    .setTag(response.getTags().get(FILE_TAG_KEY))
                    .setName(writeResponse.object())
                    .setBucket(writeResponse.bucket())
                    .setVersion(writeResponse.versionId());
        };
    }

    private Consumer<FileRecord> publishImageUploadEvent() {
        return record -> {
            boolean isImage = FileExtensionUtils.isImage(record.getName());
            if (isImage) {
                FileUploadResult result = new FileUploadResult()
                        .setContentType(record.getType())
                        .setBucket(record.getBucket())
                        .setEtag(record.getTag())
                        .setName(record.getName())
                        .setVersion(record.getVersion());
                ImageUpload request = new ImageUpload();
                request.setResult(result);
                request.setLocalDateTime(LocalDateTime.now(Clock.systemDefaultZone()));
                imageQueuePublisher.publishImageUploadEvent(request);
            }
        };
    }

}
