
package com.crcl.comment.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class FileUploadResponse {
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
