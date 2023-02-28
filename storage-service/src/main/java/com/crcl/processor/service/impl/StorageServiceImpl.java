package com.crcl.processor.service.impl;

import com.crcl.common.domain.Orientation;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.processor.domain.FileRecord;
import com.crcl.processor.exceptions.CreateRecordException;
import com.crcl.processor.exceptions.NotFoundException;
import com.crcl.processor.queue.ResizeImageQueuePublisher;
import com.crcl.processor.repository.RecordRepository;
import com.crcl.processor.service.StorageService;
import com.crcl.processor.service.UserService;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final MinioClient minioClient;
    private final RecordRepository recordRepository;
    private final BucketsResolver bucketsResolver;
    private final ResizeImageQueuePublisher resizeImageQueueSender;
    private final UserService userService;


    @Override
    public Flux<FileUploadResult> saveAll(Flux<FilePart> filePartFlux) {
        return filePartFlux.flatMap(filePart -> this.save(Mono.just(filePart)));
    }

    @Override
    @SneakyThrows(Exception.class)
    public Mono<FileUploadResult> save(Mono<FilePart> particle) {

        final var inputStreamResult = toInputStream(particle)
                .zipWith(particle);

        final var doUpload = createMinioRequest()
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
    public Mono<ByteArrayResource> getResource(String fileName, String owner, String bucket) {
        final var getObjectArgs = GetObjectArgs.builder()
                .object(fileName)
                .matchETag(owner)
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
                final var inputStream = zip.getT1();
                final var filename = zip.getT2().filename();
                int available = inputStream.available();

                return PutObjectArgs.builder()
                        .extraHeaders(zip.getT2().headers().toSingleValueMap())
                        .bucket(bucketsResolver.resolve())
                        .object(filename)
                        .stream(inputStream, available, -1)
                        .contentType(URLConnection.guessContentTypeFromName(filename))
                        .build();
            } catch (IOException e) {
                log.error("Failed to create Minio request: {}", e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        };
    }


    private Function<PutObjectArgs, ObjectWriteResponse> uploadFile() {
        return args -> {
            try {
                return minioClient.putObject(args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public Mono<InputStream> toInputStream(Mono<FilePart> particle) {
        return particle.flatMapMany(Part::content)
                .reduce(DataBuffer::write)
                .map(dataBuffer -> new ByteArrayInputStream(dataBuffer.asByteBuffer().array()));
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

    private Function<ObjectWriteResponse, FileRecord> buildFileRecord() {
        return response -> new FileRecord()
                .setType(URLConnection.guessContentTypeFromName(response.object()))
                .setTag(response.etag())
                .setName(response.object())
                .setBucket(response.bucket())
                .setVersion(response.versionId());
    }

    private Consumer<FileRecord> publishImageUploadEvent() {
        return record -> {
            boolean isImage = FileExtensionUtils.isImage(record.getName());
            if (isImage) {
                final var result = new FileUploadResult()
                        .setContentType(record.getType())
                        .setBucket(record.getBucket())
                        .setEtag(record.getTag())
                        .setName(record.getName())
                        .setVersion(record.getVersion());
                final var request = new ImageUploadEvent();
                request.setResponse(result);
                request.setLocalDateTime(LocalDateTime.now(Clock.systemDefaultZone()));
                resizeImageQueueSender.publishImageUploadEvent(request);
            }
        };
    }

    private Function<InputStream, Orientation> getOrientationMode() {
        return inputStream -> {
            try {
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
            return null;
        };
    }

}
