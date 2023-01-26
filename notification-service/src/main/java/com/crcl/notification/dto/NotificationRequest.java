package com.crcl.notification.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationRequest<T> {
    private UUID id;
    private T payload;
    private LocalDateTime createdAt;
    private boolean isAsync = false;
}
