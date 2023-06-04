package com.crcl.am.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class CreateFriendRequest {
    @NotBlank
    private String recipient;


}
