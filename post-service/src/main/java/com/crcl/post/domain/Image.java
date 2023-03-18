package com.crcl.post.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public
class Image {
    private int id;
    private String url;
    private String contentType;
    private int width;
    private int height;
}
