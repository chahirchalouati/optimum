package com.crcl.authentication.service.impl;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.mappers.FriendShipMapper;
import com.crcl.authentication.repository.FriendShipRepository;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.service.FriendShipService;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendShipServiceImpl implements FriendShipService {

    private final FriendShipRepository friendShipRepository;
    private final FriendShipMapper friendShipMapper;
    private final UserRepository userRepository;

    @Override
    public FriendShipDto create(String owner, String newFriend) {

        Pair<Boolean, FriendShip> existsPair = this.friendShipRepository.hasFriendShip(owner, newFriend);
        if (existsPair.getFirst()) {
            return friendShipMapper.toDto(existsPair.getSecond());
        }

        final var ownerEntity = userRepository.findByUsernameAllIgnoreCase(owner)
                .orElseThrow(() -> new UsernameNotFoundException("can't find user with username %s".formatted(owner)));
        final var newFriendEntity = userRepository.findByUsernameAllIgnoreCase(newFriend)
                .orElseThrow(() -> new UsernameNotFoundException("can't find user with username %s".formatted(newFriend)));
        FriendShip friendShip = this.friendShipRepository.add(ownerEntity, List.of(newFriendEntity));

        return friendShipMapper.toDto(friendShip);
    }

    @Override
    public FriendShipDto remove(UserDto owner, UserDto newFriend) {
        return null;
    }
}
