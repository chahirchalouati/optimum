package com.crcl.profile.service;


import com.crcl.common.utils.generic.GenericService;
import com.crcl.profile.dto.ProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService extends GenericService<ProfileDto, String> {
    ProfileDto findByUsername(String username);

    Page<ProfileDto> findAll(ProfileDto pageRequest, Pageable pageable);

    ProfileDto findOne(ProfileDto pageRequest);

    List<ProfileDto> findByUsernames(List<String> usernames);
}
