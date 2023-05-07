package com.crcl.authentication.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateFriendRequest {
    @NotBlank
    private String recipient;


}
