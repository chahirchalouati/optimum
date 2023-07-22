package com.crcl.common.dto.requests;

import lombok.Data;

@Data
public class ReactionSearchRequest {
    private ReactionType type;
    private String targetId;
    private String entityName;
}
