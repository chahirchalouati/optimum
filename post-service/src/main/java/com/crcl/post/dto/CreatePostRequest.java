package com.crcl.post.dto;

import com.crcl.post.domain.Access;
import com.crcl.post.domain.Tag;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreatePostRequest {
    private ArrayList<MultipartFile> files;
    private String content;
    private Access access;
    private List<String> sharedWithUsers = new ArrayList<>();
    private List<String> taggedUsers = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private String location;
}
