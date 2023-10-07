package com.crcl.comment.clients;

import com.crcl.comment.configuration.security.FeignFormConfig;
import com.crcl.comment.configuration.security.OAuthFeignConfig;
import com.crcl.comment.dto.FileUploadResponse;
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
                FeignFormConfig.class}
)
public interface StorageClient {

    @GetMapping("/files/{tag}/{objectName}")
    ByteArrayResource getObject(@PathVariable("objectName") String objectName, @PathVariable("tag") String tag);

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FileUploadResponse save(@RequestPart("file") MultipartFile multipartFile);

    @PostMapping(value = "/files/all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<FileUploadResponse> saveAll(@RequestPart("files") List<MultipartFile> multipartFiles);
}
