package com.crcl.storage.controller;

import com.crcl.storage.service.StorageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "get stored file by owner & fileName")})
    @GetMapping("/{owner}/{fileName}")
    public Mono<ResponseEntity<?>> getByTagAndFileName(@PathVariable("fileName") String fileName, @PathVariable("owner") String owner) {
        return storageService.getResource(fileName, owner)
                .map(byteArrayResource -> ResponseEntity.ok()
                        .contentType(MediaType.valueOf(URLConnection.guessContentTypeFromName(fileName)))
                        .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePrivate().proxyRevalidate())
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(byteArrayResource.contentLength()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"%s\"".formatted(fileName))
                        .body(byteArrayResource));
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "store file")})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<?>> store(@RequestPart("file") Mono<FilePart> multipartFile) {
        return Mono.just(new ResponseEntity<>(storageService.save(multipartFile), HttpStatus.OK));
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "store files")})
    @PostMapping(value = "/all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<?>> storeAll(@RequestPart("files") Flux<FilePart> multipartFiles) {
        return Mono.just(new ResponseEntity<>(storageService.saveAll(multipartFiles), HttpStatus.OK));
    }
}
