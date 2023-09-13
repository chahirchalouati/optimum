package com.crcl.post.dto;

import com.crcl.post.annotations.HasPostContentOrFiles;
import com.crcl.post.domain.Access;
import com.crcl.post.domain.Tag;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@HasPostContentOrFiles
public class CreatePostRequest {
    @Getter(AccessLevel.NONE)
    private MultipartFile[] files;
    private String content;
    private Access access;
    private List<String> sharedWithUsers = new ArrayList<>();
    private List<String> taggedUsers = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private String location;

    public List<MultipartFile> getFiles() {
        return Objects.nonNull(files) && files.length > 0 ? List.of(files) : List.of();
    }
}
