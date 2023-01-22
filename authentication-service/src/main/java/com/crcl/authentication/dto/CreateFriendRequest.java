package com.crcl.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateFriendRequest {
    @NotBlank
    @JsonProperty("user")
    private String ownerUsername;
    @NotBlank
    @JsonProperty("friend")
    private String newFriendUsername;


}
