package com.crcl.common.dto.requests;

import com.crcl.common.utils.NotificationDefinition;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class NotificationRequest<T> {
    private UUID id = UUID.randomUUID();
    private T payload;
    @NotNull
    private NotificationDefinition type;
    @NotNull
    private String sender;
    private LocalDateTime createdAt = LocalDateTime.now(Clock.systemDefaultZone());


}
