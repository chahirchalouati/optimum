package com.crcl.storage.service;

import com.crcl.storage.dto.FileUploadResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface StorageService {
    Mono<ByteArrayResource> getResource(String objectName, String eTag);

    Mono<FileUploadResponse> save(MultipartFile multipartFile);

    Flux<FileUploadResponse> saveAll(Collection<MultipartFile> multipartFiles);
}
