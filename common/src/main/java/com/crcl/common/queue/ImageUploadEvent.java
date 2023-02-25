package com.crcl.common.queue;

import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageUploadEvent extends BaseEvent {
    private FileUploadResult response;
    private ImageSize imageSize;
    private String id;
}
