package com.crcl.post.domain;

import com.crcl.common.dto.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
@Accessors(chain = true)
public class Post {
    @Id
    private String id;
    private String content;
    private Profile creator;
    private Access access = Access.PUBLIC;
    private Set<Video> videos = new HashSet<>();
    private Set<Image> images = new HashSet<>();
    private Set<Tag> tags = new HashSet<>();
    private Set<Like> likes = new HashSet<>();
    private Set<UserDto> sharedWithUsers = new HashSet<>();
}
