<<<<<<<< HEAD:post-service/src/main/java/com/crcl/post/dto/FileUploadResult.java
package com.crcl.post.dto;

========

package com.crcl.comment.dto;
>>>>>>>> develop-v2:comment-service/src/main/java/com/crcl/comment/dto/FileUploadResponse.java
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
<<<<<<<< HEAD:post-service/src/main/java/com/crcl/post/dto/FileUploadResult.java
public class FileUploadResult {
    private String etag;
========
public class FileUploadResponse {
  private String etag;
>>>>>>>> develop-v2:comment-service/src/main/java/com/crcl/comment/dto/FileUploadResponse.java
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
