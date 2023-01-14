package com.crcl.storage.service;

import com.crcl.storage.dto.FileUploadResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StorageService {
    Mono<ByteArrayResource> getResource(String objectName, String eTag);

    Mono<FileUploadResponse> save(Mono<FilePart> filePartMono);

    Flux<FileUploadResponse> saveAll(Flux<FilePart> filePartFlux);
}
