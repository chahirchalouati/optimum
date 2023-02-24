package com.crcl.common.queue;

import com.crcl.common.dto.responses.FileUploadResponse;
import com.crcl.common.properties.ImageSize;
import lombok.Data;

@Data
public class ImageUpload {
    private FileUploadResponse response;
    private ImageSize imageSize;
}
