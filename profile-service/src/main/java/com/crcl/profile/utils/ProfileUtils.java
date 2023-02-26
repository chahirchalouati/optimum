package com.crcl.profile.utils;

import com.crcl.profile.domain.UserDto;
import com.crcl.profile.dto.ProfileDto;
import org.jetbrains.annotations.NotNull;

public class ProfileUtils {
    public static final String DEFAULT_MALE_AVATAR = "https://thumbs.dreamstime.com/b/default-avatar-photo-placeholder-profile-icon-eps-file-easy-to-edit-default-avatar-photo-placeholder-profile-icon-124557887.jpg";
    public static final String DEFAULT_FEMALE_AVATAR = "https://thumbs.dreamstime.com/b/default-female-avatar-profile-picture-icon-grey-woman-photo-placeholder-vector-illustration-88413637.jpg";
    public static final String DEFAULT_BG_IMAGE = "https://img.freepik.com/free-vector/blue-gradient-blank-background-business_53876-120508.jpg?w=2000";

    public static ProfileDto getDefaultProfile(UserDto user) {
        return new ProfileDto()
                .setUser(user)
                .setUsername(user.getUsername())
                .setAvatar(getAvatar(user))
                .setBackgroundImage(DEFAULT_BG_IMAGE);
    }

    @NotNull
    public static String getAvatar(UserDto user) {
        return user.getGender()
                .equals("MALE") ? DEFAULT_MALE_AVATAR : DEFAULT_FEMALE_AVATAR;
    }
}
