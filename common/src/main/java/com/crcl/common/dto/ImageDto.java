package com.crcl.common.dto;

import com.crcl.common.properties.ImageSize;
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
public class ImageDto extends GenericFileDto {
    private ImageSize imageSize;

    private List<ImageDto> processedImageDtos = new ArrayList<>();

    public ImageDto(Integer index) {
        super(index);
    }

    @Override
    public ImageDto setIndex(Integer index) {
        super.setIndex(index);
        return this;
    }

    @Override
    public ImageDto setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public ImageDto setParent(String parent) {
        super.setParent(parent);
        return this;
    }

    @Override
    public ImageDto setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public ImageDto setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }


}
