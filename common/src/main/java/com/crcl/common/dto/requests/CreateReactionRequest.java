package com.crcl.common.dto.requests;

import com.crcl.common.dto.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReactionRequest {
    @NotNull(message = "Type: can't be null")
    private ReactionType type;
    @NotNull(message = "TargetId: can't be null")
    private String targetId;
    @NotNull(message = "Entity: can't be null")
    private Entity entity;

}
