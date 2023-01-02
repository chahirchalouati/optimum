package com.crcl.post.dto;

import lombok.Data;

@Data
public class FileUploadResponse {
    private String etag;
    private String name;
    private String bucket;
    private String version;
}
