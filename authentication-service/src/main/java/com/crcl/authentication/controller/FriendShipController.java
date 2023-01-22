package com.crcl.authentication.controller;

import com.crcl.authentication.dto.CreateFriendRequest;
import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.authentication.service.FriendShipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("friends")
@AllArgsConstructor
public class FriendShipController {
    private final FriendShipService friendShipService;

    @PostMapping("/create")
    public ResponseEntity<FriendShipDto> create(@RequestBody @Valid CreateFriendRequest createFriendRequest) {
        return ResponseEntity.ok(friendShipService.create(createFriendRequest.getOwnerUsername(), createFriendRequest.getNewFriendUsername()));
    }

}
