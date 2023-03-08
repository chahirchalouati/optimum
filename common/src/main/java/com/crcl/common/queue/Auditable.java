package com.crcl.common.queue;

import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
public class Auditable {
    protected LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
}
