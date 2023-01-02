package com.crcl.post.client;

import com.crcl.post.configuration.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "${client.storage.name}", url = "${client.storage.url}", configuration = OAuthFeignConfig.class)
public interface StorageClient {

    @GetMapping("/{objectName}/{tag}")
    ResponseEntity<?> getObject(@PathVariable("objectName") String objectName, @PathVariable("tag") String tag);

    @PostMapping()
    ResponseEntity<?> save(@RequestParam("file") MultipartFile multipartFile);

    @PostMapping("/all")
    ResponseEntity<?> saveAll(@RequestParam("files") MultipartFile[] multipartFile);
}
