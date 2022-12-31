package com.crcl.profile.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileDto save(ProfileDto profileDto) {
        Profile profile = this.profileMapper.toEntity(profileDto);
        return profileMapper.toDto(profileRepository.save(profile));
    }

    @Override
    public List<ProfileDto> save(List<ProfileDto> entities) {
        return entities.stream().map(this::save).toList();
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
                .map(profileMapper::toDto)
                .toList();
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
        return profileRepository.findByUsername(username)
                .map(profileMapper::toDto)
                .orElse(null);
    }

    @Override
    public Page<ProfileDto> findAll(ProfileDto pageRequest, Pageable pageable) {
        return this.profileRepository.findAll(Example.of(profileMapper.toEntity(pageRequest)), pageable).map(profileMapper::toDto);
    }

    @Override
    public ProfileDto findOne(ProfileDto pageRequest) {
        return this.profileRepository.findOne(Example.of(profileMapper.toEntity(pageRequest)))
                .map(profileMapper::toDto)
                .orElse(null);
    }
}