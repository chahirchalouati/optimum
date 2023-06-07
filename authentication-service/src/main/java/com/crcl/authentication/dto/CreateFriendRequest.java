package com.crcl.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFriendRequest {
    @NotBlank
    private String recipient;


}
