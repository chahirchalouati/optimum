package com.crcl.core.dto.queue;

import com.crcl.core.dto.responses.FileUploadResult;
import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
public class ProcessableVideoEvent {
    protected LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
    private FileUploadResult result;
    private String id;
}
