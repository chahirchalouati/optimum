package com.crcl.authentication.service.impl;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.FriendShipState;
import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.mappers.FriendShipMapper;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.repository.FriendShipRepository;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.service.FriendShipService;
import com.crcl.authentication.service.UserService;
import com.crcl.common.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class FriendShipServiceImpl implements FriendShipService {

    private final FriendShipRepository friendShipRepository;
    private final FriendShipMapper friendShipMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public FriendShipDto create(String recipient) {
        var sender = userService.getCurrentUser().getUsername();
        var friendShip = this.friendShipRepository.areFriends(sender, recipient);
        if (Objects.equals(sender, recipient)) {
            throw new BadRequestException("please choose a correct recipient");
        }
        if (friendShip.isPresent()) {
            return friendShipMapper.toDto(friendShip.get());
        }

        var senderEntity = userRepository.findByUsernameAllIgnoreCase(sender)
                .orElseThrow(() -> new UsernameNotFoundException("can't find user with username %s".formatted(sender)));
        var recipientEntity = userRepository.findByUsernameAllIgnoreCase(recipient)
                .orElseThrow(() -> new UsernameNotFoundException("can't find user with username %s".formatted(recipient)));
        FriendShip linkedFriendShip = this.friendShipRepository.link(senderEntity, recipientEntity, FriendShipState.PENDING);

        return friendShipMapper.toDto(linkedFriendShip);
    }

    @Override
    public FriendShipDto remove(UserDto sender, UserDto recipient) {
        return null;
    }

    @Override
    public Page<UserDto> findFriends(String username, Pageable pageable) {
        return friendShipRepository.findFriends(username, pageable)
                .map(friendShip -> friendShip.getSender().getUsername().equals(username) ? friendShip.getRecipient() : friendShip.getSender())
                .map(userMapper::toDto);
    }
}
