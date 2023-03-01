package com.crcl.common.dto;

import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResizedImageDetails {
    private ImageSize dimensions;
    private FileUploadResult details;
}
