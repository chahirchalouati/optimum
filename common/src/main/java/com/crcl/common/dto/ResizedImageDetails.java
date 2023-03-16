package com.crcl.common.dto;

import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResizedImageDetails {
    private ImageSize dimensions;
    private FileUploadResult details;
}
