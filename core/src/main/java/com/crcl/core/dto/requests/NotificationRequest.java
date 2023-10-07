package com.crcl.core.dto.requests;

import com.crcl.core.utils.NotificationDefinition;
import com.crcl.core.utils.NotificationTargets;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class NotificationRequest<T> {
    private UUID id = UUID.randomUUID();
    private T payload;
    @NotNull
    private NotificationDefinition notificationDefinition;
    @NotNull
    private String sender;
    private LocalDateTime createdAt = LocalDateTime.now(Clock.systemDefaultZone());
    @NotNull
    private List<String> targets = new ArrayList<>();
    @NotNull
    private NotificationTargets notificationTargets;
}
