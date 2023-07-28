package com.crcl.core.dto.queue;

import com.crcl.core.domain.Orientation;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.properties.ImageSize;
import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
public class ProcessableImage {
    protected LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
    private FileUploadResult result;
    private ImageSize size;
    private String id;
    private Orientation orientation;
}
