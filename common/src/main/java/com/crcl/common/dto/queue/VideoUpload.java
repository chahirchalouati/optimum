package com.crcl.common.dto.queue;

import com.crcl.common.dto.responses.FileUploadResult;
import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
public class VideoUpload {
    protected LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
    private FileUploadResult result;
    private String id;
}
