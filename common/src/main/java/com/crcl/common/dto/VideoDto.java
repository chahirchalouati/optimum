package com.crcl.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class VideoDto extends GenericFileDto {
    private String title;
    private String description;
    private int lengthInSeconds;
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
