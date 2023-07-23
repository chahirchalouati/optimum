package com.crcl.utilities.dto;

import com.crcl.common.dto.UserDto;
import com.crcl.common.dto.requests.ReactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReactionDto {
    private String id;
    private UserDto reactingUser;
    private ReactionType type;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modifiedAt;
}
