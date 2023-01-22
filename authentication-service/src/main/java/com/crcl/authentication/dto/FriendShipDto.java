package com.crcl.authentication.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FriendShipDto {
    private UserDto owner;
    private Set<UserDto> friends = new HashSet<>();
}
