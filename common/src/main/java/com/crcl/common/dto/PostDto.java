package com.crcl.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDto {
    private String id;
    private int commentCount = 0;
    private int shareCount = 0;
    private int likesCount = 0;
    private int disLikesCount = 0;
    private String content;
    private ProfileDto creator;
    private AccessDto access = AccessDto.PUBLIC;
    private Set<VideoDto> videos = new HashSet<>();
    private Set<ImageDto> images = new HashSet<>();
    private Set<GenericFileDto> genericFiles = new HashSet<>();
    private Set<TagDto> tags = new HashSet<>();
    private Set<LikeDto> likes = new HashSet<>();
    private Set<UserDto> sharedWithUsers = new HashSet<>();
    private Map<String, Boolean> actions = new HashMap<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private LocalDateTime createDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private LocalDateTime lastModifiedDate;
}
