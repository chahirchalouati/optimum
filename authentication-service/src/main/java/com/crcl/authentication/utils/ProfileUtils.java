package com.crcl.authentication.utils;

import com.crcl.authentication.domain.User;
import com.crcl.authentication.dto.ProfileDto;

public class ProfileUtils {
    private static final String DEFAULT_AVATAR = "";
    private static final String DEFAULT_BG_IMAGE = "";

    public static ProfileDto getDefaultProfile(User user) {
        return new ProfileDto()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAvatar(DEFAULT_AVATAR)
                .setBackgroundImage(DEFAULT_BG_IMAGE);
    }
}
