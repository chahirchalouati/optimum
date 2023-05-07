package com.crcl.post.dto;

import com.crcl.common.dto.ProfileDto;
import com.crcl.common.dto.UserDto;
import com.crcl.post.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Data
@Accessors(chain = true)
public class PostDto {
    private String id;
    private String content;
    private ProfileDto creator;
    private Access access = Access.PUBLIC;
    private Set<Video> videos = new HashSet<>();
    private Set<Image> images = new HashSet<>();
    private Set<Tag> tags = new HashSet<>();
    private Set<Like> likes = new HashSet<>();
    private Set<UserDto> sharedWithUsers = new HashSet<>();
    private int commentCount = 0;
    private int shareCount = 0;
    private int likesCount = 0;
    private int disLikesCount = 0;
    private Map<String, Boolean> actions = new HashMap<>();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastModifiedDate;
}
