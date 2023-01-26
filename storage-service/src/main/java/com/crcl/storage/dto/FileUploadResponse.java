package com.crcl.storage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadResponse {
    private String etag;
    private String name;
    private String bucket;
    private String version;
    private String contentType;
}
