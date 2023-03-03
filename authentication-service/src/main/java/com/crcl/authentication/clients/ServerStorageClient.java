package com.crcl.authentication.clients;

import com.crcl.authentication.configuration.clients.FeignFormConfig;
import com.crcl.authentication.configuration.clients.SrvOauth2ClientConfig;
import com.crcl.common.dto.responses.FileUploadResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// TODO: 1/8/2023 add resiliency
@FeignClient(name = "${client.srvStorage.name}",
        url = "${client.storage.url}",
        configuration = {SrvOauth2ClientConfig.class, FeignFormConfig.class})
public interface ServerStorageClient {

    @GetMapping("/files/{tag}/{objectName}")
    ByteArrayResource getObject(@PathVariable("objectName") String objectName, @PathVariable("tag") String tag);

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FileUploadResult save(@RequestPart("file") MultipartFile multipartFile);

    @PostMapping(value = "/files/all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<FileUploadResult> saveAll(@RequestPart("files") List<MultipartFile> multipartFiles);
}
