package com.crcl.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class AttachmentDto {
    private String etag;
    private String name;
    private String bucket;
    private String version;
    @Getter(AccessLevel.NONE)
    @JsonIgnore
    private String link;
}
