package com.crcl.core.dto.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReactionSearchRequest extends PageableSearchRequest {
    private ReactionType type;
    private String targetId;
    private String entityName;

}
