package com.crcl.profile.service;


import com.crcl.common.utils.generic.GenericService;
import com.crcl.profile.dto.ProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService extends GenericService<ProfileDto, String> {
    ProfileDto findByUsername(String username);

    Page<ProfileDto> findAll(ProfileDto pageRequest, Pageable pageable);
}
