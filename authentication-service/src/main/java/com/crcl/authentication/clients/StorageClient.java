package com.crcl.authentication.clients;

import com.crcl.authentication.configuration.clients.FeignFormConfig;
import com.crcl.authentication.configuration.clients.OAuthFeignConfig;
import com.crcl.core.dto.responses.FileUploadResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@FeignClient(name = "${client.storage.name}",
        url = "${client.storage.url}",
        configuration = {
                OAuthFeignConfig.class,
                FeignFormConfig.class
        })
public interface StorageClient {

    @GetMapping("/files/{folder}/{fileName}")
    ByteArrayResource getObject(@PathVariable("fileName") String fileName, @PathVariable("folder") String folder);

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FileUploadResult save(@RequestPart("file") MultipartFile multipartFile);

    @PostMapping(value = "/files/all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<FileUploadResult> saveAll(@RequestPart("files") List<MultipartFile> multipartFiles);
}
