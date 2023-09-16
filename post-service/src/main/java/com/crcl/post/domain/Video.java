package com.crcl.post.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Video extends GenericFile {
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

    public Video(Integer index) {
        super(index);
    }
}
