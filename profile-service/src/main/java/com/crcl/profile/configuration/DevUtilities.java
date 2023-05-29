package com.crcl.profile.configuration;

import com.crcl.common.dto.UserDto;
import com.crcl.profile.client.SrvIdpClient;
import com.crcl.profile.dto.ProfileDto;
import com.crcl.profile.mappers.ProfileMapper;
import com.crcl.profile.repository.ProfileRepository;
import com.crcl.profile.utils.ProfileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Configuration
@RequiredArgsConstructor
@Profile({"dev", "docker"})
@Slf4j
public class DevUtilities {
    private final SrvIdpClient srvIdpClient;
    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("Starting command line runner");
        Page<UserDto> users;
        do {
            users = srvIdpClient.findAll(PageRequest.of(0, 10));
            log.debug("Retrieved {} users from SrvIdpClient", users.stream().count());
            users.forEach(
                    user -> {
                        if (profileRepository.findByUsername(user.getUsername()).isEmpty()) {
                            log.info("User profile not found, creating a new profile for user: {}", user.getUsername());
                            ProfileDto profileDto = ProfileUtils.getDefaultProfile(user);
                            profileRepository.save(profileMapper.toEntity(profileDto));
                            log.info("New profile created for user: {}", user.getUsername());
                        }
                    });
        } while (!users.isLast());

        log.info("Finished command line runner");
    }

}
