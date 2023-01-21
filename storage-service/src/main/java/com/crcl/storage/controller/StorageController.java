package com.crcl.storage.controller;

import com.crcl.storage.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLConnection;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("files")
@AllArgsConstructor
@Slf4j
public class StorageController {
    private final StorageService storageService;

    @GetMapping("/{tag}/{objectName}")
    public Mono<ResponseEntity<?>> getObject(@PathVariable("objectName") String objectName, @PathVariable("tag") String tag) {
        return storageService.getResource(objectName, tag)
                .map(byteArrayResource -> ResponseEntity.ok()
                        .contentType(MediaType.valueOf(URLConnection.guessContentTypeFromName(objectName)))
                        .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePrivate().proxyRevalidate())
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(byteArrayResource.contentLength()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"%s\"".formatted(objectName))
                        .body(byteArrayResource));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<?>> save(@RequestPart("file") Mono<FilePart> multipartFile) {
        return Mono.just(new ResponseEntity<>(storageService.save(multipartFile), HttpStatus.OK));
    }

    @PostMapping(value = "/all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<?>> saveAll(@RequestPart("files") Flux<FilePart> multipartFiles) {
        return Mono.just(new ResponseEntity<>(storageService.saveAll(multipartFiles), HttpStatus.OK));
    }
}
