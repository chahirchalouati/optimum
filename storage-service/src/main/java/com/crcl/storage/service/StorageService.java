package com.crcl.storage.service;

import com.crcl.storage.dto.FileUploadResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    ByteArrayResource getResource(String objectName, String eTag);

    FileUploadResponse save(MultipartFile multipartFile);

    List<FileUploadResponse> saveAll(MultipartFile[] multipartFiles);
}
