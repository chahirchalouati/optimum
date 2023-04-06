package com.crcl.authentication.dto;

import com.crcl.authentication.domain.FriendShipState;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class FriendShipDto {
    private UserDto sender;
    private UserDto recipient;
    private FriendShipState state;
    private LocalDateTime createdAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime deletedAt;
}
