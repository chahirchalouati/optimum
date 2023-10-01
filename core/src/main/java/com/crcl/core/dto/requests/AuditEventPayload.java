package com.crcl.core.dto.requests;

import com.crcl.core.utils.AuditAction;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class AuditEventPayload {
    protected String username;
    private String identifier;
    private AuditAction action;
    private Map<String, Object> details = new LinkedHashMap<>();
    private LocalDateTime createdAt;
}
