package com.crcl.post.domain;

import com.crcl.common.properties.ImageSize;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Image {
    private Integer index;
    private String id;
    private String parent;
    private String url;
    private String contentType;
    private ImageSize imageSize;
    private List<Image> processedImages = new ArrayList<>();
}
