package com.crcl.post.domain;

import com.crcl.common.dto.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public
class Video {
    private int id;
    private String title;
    private String description;
    private int lengthInSeconds;
    private String url;
    private boolean isPublic;
    private String[] tags;
    private String resolution;
    private String format;
    private int bitrate;
    private String codec;
    private int viewCount;
    private int likeCount;
    private int commentCount;
    private String thumbnailUrl;

}
