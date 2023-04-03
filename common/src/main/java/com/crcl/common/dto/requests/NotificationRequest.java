package com.crcl.common.dto.requests;

import com.crcl.common.dto.NotificationType;
import com.crcl.common.utils.NotificationTargets;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class NotificationRequest<T> {
    private UUID id = UUID.randomUUID();
    private T payload;
    private NotificationType type;
    private LocalDateTime createdAt = LocalDateTime.now(Clock.systemDefaultZone());


}
