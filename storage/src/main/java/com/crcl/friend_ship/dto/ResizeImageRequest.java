package com.crcl.friend_ship.dto;

import com.crcl.friend_ship.domain.FileRecord;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ResizeImageRequest {
    private FileRecord fileRecord;
    private LocalDateTime createdAt = LocalDateTime.now(Clock.systemDefaultZone());
}
