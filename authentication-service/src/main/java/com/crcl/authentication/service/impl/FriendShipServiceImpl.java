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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendShipServiceImpl implements FriendShipService {

    private final FriendShipRepository friendShipRepository;
    private final FriendShipMapper friendShipMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public FriendShipDto create(String owner, String newFriend) {

        var friendShip = this.friendShipRepository.hasFriendShip(owner, newFriend);
        if (friendShip.isPresent()) {
            return friendShipMapper.toDto(friendShip.get());
        }

        final var ownerEntity = userRepository.findByUsernameAllIgnoreCase(owner)
                .orElseThrow(() -> new UsernameNotFoundException("can't find user with username %s".formatted(owner)));
        final var newFriendEntity = userRepository.findByUsernameAllIgnoreCase(newFriend)
                .orElseThrow(() -> new UsernameNotFoundException("can't find user with username %s".formatted(newFriend)));
        FriendShip linkedFriendShip = this.friendShipRepository.link(ownerEntity, newFriendEntity, FriendShipState.PENDING, owner);

        return friendShipMapper.toDto(linkedFriendShip);
    }

    @Override
    public FriendShipDto remove(UserDto owner, UserDto newFriend) {
        return null;
    }

    @Override
    public Page<UserDto> findFriends(String username, Pageable pageable) {
        return friendShipRepository.findFriends(username, pageable)
                .map(friendShip -> friendShip.getRight().getUsername().equals(username) ? friendShip.getLeft() : friendShip.getRight())
                .map(userMapper::toDto);
    }
}
