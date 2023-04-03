package com.crcl.common.dto.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationResponse<T> {
    private UUID id;
    private T payload;
    private LocalDateTime sentAt;
}
