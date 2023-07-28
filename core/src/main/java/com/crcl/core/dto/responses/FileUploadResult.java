package com.crcl.core.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileUploadResult {
    private String etag;
    private String name;
    private String bucket;
    private String version;
    private String contentType;
    @Getter(AccessLevel.NONE)
    @JsonIgnore
    private String link;

    public String getLink() {
        return this.etag.concat("/").concat(this.name);
    }
}
