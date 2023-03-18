package com.crcl.post.dto;

import com.crcl.post.domain.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Data
@Accessors(chain = true)
public class PostDto {
    private String id;
    private String content;
    private Profile creator;
    private Access access = Access.PUBLIC;
    private Set<Video> videos = new HashSet<>();
    private Set<Image> images = new HashSet<>();
    private Set<Tag> tags = new HashSet<>();
    private Set<Like> likes = new HashSet<>();
    private int commentCount = 0;
    private int shareCount = 0;
    private int likesCount = 0;
    private int disLikesCount = 0;
    private Map<String, Boolean> actions = new HashMap<>();
}
