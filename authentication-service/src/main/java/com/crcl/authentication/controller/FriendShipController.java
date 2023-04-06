package com.crcl.authentication.controller;

import com.crcl.authentication.dto.CreateFriendRequest;
import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.service.FriendShipService;
import com.crcl.authentication.views.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("friends")
@AllArgsConstructor
public class FriendShipController {
    private final FriendShipService friendShipService;

    @JsonView(UserView.UserResponseView.class)
    @PostMapping("/create")
    public ResponseEntity<FriendShipDto> create(@RequestBody @Valid CreateFriendRequest createFriendRequest) {
        return ResponseEntity.ok(friendShipService.create(createFriendRequest.getRecipient()));
    }

    @JsonView(UserView.UserResponseView.class)
    @GetMapping("/user/{username}")
    public ResponseEntity<Page<UserDto>> findFriends(@PathVariable("username") String username, Pageable pageable) {
        return ResponseEntity.ok(friendShipService.findFriends(username,pageable));
    }

}
