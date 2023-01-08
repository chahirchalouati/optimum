package com.crcl.authentication.utils;

import com.crcl.authentication.domain.Gender;
import com.crcl.authentication.domain.User;
import com.crcl.authentication.dto.ProfileDto;
import org.jetbrains.annotations.NotNull;

public class ProfileUtils {
    private static final String DEFAULT_MALE_AVATAR = "https://thumbs.dreamstime.com/b/default-avatar-photo-placeholder-profile-icon-eps-file-easy-to-edit-default-avatar-photo-placeholder-profile-icon-124557887.jpg";
    private static final String DEFAULT_FEMALE_AVATAR = "https://thumbs.dreamstime.com/b/default-female-avatar-profile-picture-icon-grey-woman-photo-placeholder-vector-illustration-88413637.jpg";
    private static final String DEFAULT_BG_IMAGE = "https://img.freepik.com/free-vector/blue-gradient-blank-background-business_53876-120508.jpg?w=2000";

    public static ProfileDto getDefaultProfile(User user) {
        return new ProfileDto().setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAvatar(getAvatar(user)).setBackgroundImage(DEFAULT_BG_IMAGE);
    }

    @NotNull
    public static String getAvatar(User user) {
        return user.getGender()
                .equals(Gender.MALE) ? DEFAULT_MALE_AVATAR : DEFAULT_FEMALE_AVATAR;
    }
}
