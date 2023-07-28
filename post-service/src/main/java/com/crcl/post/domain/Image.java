package com.crcl.post.domain;

import com.crcl.core.properties.ImageSize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Image extends GenericFile {
    private ImageSize imageSize;

    private List<Image> processedImages = new ArrayList<>();

    public Image(Integer index) {
        super(index);
    }

    @Override
    public Image setIndex(Integer index) {
        super.setIndex(index);
        return this;
    }

    @Override
    public Image setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public Image setParent(String parent) {
        super.setParent(parent);
        return this;
    }

    @Override
    public Image setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public Image setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }


}
