package com.crcl.profile.configuration;

import com.crcl.profile.client.IdpClient;
import com.crcl.profile.domain.UserDto;
import com.crcl.profile.dto.ProfileDto;
import com.crcl.profile.mappers.ProfileMapper;
import com.crcl.profile.repository.ProfileRepository;
import com.crcl.profile.utils.ProfileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
@Slf4j
public class DevUtilities {

    @Bean
    public CommandLineRunner commandLineRunner(final IdpClient idpClient,
                                               final ProfileMapper profileMapper,
                                               final ProfileRepository profileRepository) {
        return args -> {
            log.info("Starting command line runner");
            final List<UserDto> users = idpClient.findAll();
            for (UserDto user : users) {
                if (profileRepository.findByUsername(user.getUsername()).isEmpty()) {
                    log.info("User profile not found, creating a new profile for user: {}", user.getUsername());
                    ProfileDto profileDto = ProfileUtils.getDefaultProfile(user);
                    profileRepository.save(profileMapper.toEntity(profileDto));
                    log.info("New profile created for user: {}", user.getUsername());
                }
            }
            log.info("Finished command line runner");
        };
    }

}
