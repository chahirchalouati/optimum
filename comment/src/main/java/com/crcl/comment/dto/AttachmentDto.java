package com.crcl.comment.dto;

import com.crcl.core.domain.Orientation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
public class AttachmentDto {
    private String etag;
    private String name;
    private String bucket;
    private String version;
    private String contentType;
    private Map<String, Object> additionalData;
    private Orientation orientation;
    @Getter(AccessLevel.NONE)
    @JsonIgnore
    private String link;
}
