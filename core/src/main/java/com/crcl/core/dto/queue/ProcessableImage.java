package com.crcl.core.dto.queue;

import com.crcl.core.domain.Orientation;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.properties.ImageSize;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ProcessableImage {
    private LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
    private FileUploadResult result;
    private ImageSize size;
    private String id;
    private Orientation orientation;
}
