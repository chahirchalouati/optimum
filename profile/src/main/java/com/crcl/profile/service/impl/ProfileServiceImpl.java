package com.crcl.profile.service.impl;

import com.crcl.core.dto.UserDto;
import com.crcl.core.exceptions.EntityNotFoundException;
import com.crcl.core.utils.NotificationDefinition;
import com.crcl.core.utils.NotificationTargets;
import com.crcl.profile.client.IdpClient;
import com.crcl.profile.domain.Profile;
import com.crcl.profile.dto.ProfileDto;
import com.crcl.profile.mappers.ProfileMapper;
import com.crcl.profile.repository.ProfileRepository;
import com.crcl.profile.service.ProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final IdpClient idpClient;

    @Override
    public ProfileDto save(ProfileDto profileDto) {
        Profile profile = this.profileMapper.toEntity(profileDto);

        UserDto userDto = this.idpClient.findByUsername(profileDto.getUsername());
        if (Objects.isNull(userDto))
            throw new UsernameNotFoundException("unable to find user for profile with username " + profileDto.getUsername());
        profile.setUser(userDto);
        return profileMapper.toDto(profileRepository.save(profile));
    }

    @Override
    public List<ProfileDto> saveAll(List<ProfileDto> entities) {
        return entities.stream()
                .map(this::save).toList();
    }

    @Override
    public void deleteById(String id) {
        profileRepository.findById(id).ifPresent(profile -> {
            profileRepository.save(profile);
            log.info("profile with id %s was disabled".formatted(profile.getId()));
        });

    }

    @Override
    public ProfileDto findById(String id) {
        return profileRepository.findById(id)
                .map(profileMapper::toDto)
                .orElse(null);
    }


    @Override
    public List<ProfileDto> findAll() {
        return profileRepository.findAll().stream()
                .map(profileMapper::toDto).toList();
    }

    @Override
    public Page<ProfileDto> findAll(Pageable pageable) {
        return profileRepository.findAll(pageable)
                .map(profileMapper::toDto);
    }

    @Override
    public ProfileDto update(ProfileDto profileDto, String id) {
        return profileRepository.findById(id)
                .map(profile -> profileMapper.toEntity(profileDto))
                .map(profileRepository::save)
                .map(profileMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProfileDto findByUsername(String username) {
        UserDto user = idpClient.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("Enable to find user with username: " + username);
        }

        return profileRepository.findByUsername(username)
                .map(profileMapper::toDto)
                .map(profileDto -> profileDto.setUser(user))
                .orElse(null);
    }

    @Override
    public Page<ProfileDto> findAll(ProfileDto pageRequest, Pageable pageable) {
        return this.profileRepository.findAll(Example.of(profileMapper.toEntity(pageRequest)), pageable)
                .map(profileMapper::toDto);
    }

    @Override
    public ProfileDto findOne(ProfileDto pageRequest) {
        return this.profileRepository.findOne(Example.of(profileMapper.toEntity(pageRequest)))
                .map(profileMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ProfileDto> findByUsernames(List<String> usernames) {
        List<Profile> profiles = this.profileRepository.findByUsernameIn(usernames);
        return profileMapper.mapToDto(profiles);
    }

    @Override
    public NotificationTargets getNotificationSettings(String username, NotificationDefinition notificationDefinition) {
        return findByUsername(username).getSettings()
                .getNotificationSettings()
                .getTargetsSettings()
                .getOrDefault(notificationDefinition, null);
    }
}
