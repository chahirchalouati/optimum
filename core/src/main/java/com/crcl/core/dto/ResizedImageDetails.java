package com.crcl.core.dto;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.properties.ImageSize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResizedImageDetails {
    private ImageSize dimensions;
    private FileUploadResult details;
}
