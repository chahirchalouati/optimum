package com.crcl.am.dto;

import com.crcl.am.domain.FriendShipState;
import lombok.Data;

import java.time.LocalDateTime;

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
