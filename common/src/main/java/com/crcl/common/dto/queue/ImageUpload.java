package com.crcl.common.dto.queue;

import com.crcl.common.domain.Orientation;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
public class ImageUpload {
    protected LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
    private FileUploadResult result;
    private ImageSize size;
    private String id;
    private Orientation orientation;
}
