package com.crcl.processor.service;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.processor.domain.FileRecord;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StorageService {
    Mono<ByteArrayResource> getResource(String fileName, String owner, String bucket);

    Mono<ByteArrayResource> getResource(FileRecord fileRecord);

    Mono<FileUploadResult> save(Mono<FilePart> filePartMono);

    Flux<FileUploadResult> saveAll(Flux<FilePart> filePartFlux);
}
