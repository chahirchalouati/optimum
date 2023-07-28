package com.crcl.profile.dto;

import com.crcl.core.dto.UserDto;
import com.crcl.profile.annotation.UniqueUserName;
import com.crcl.profile.domain.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ProfileDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @UniqueUserName
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto user;

    private String avatar;

    private String backgroundImage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Occupation occupation;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Hometown hometown;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Location> location = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Interest> interests = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Education> education = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<WorkExperience> workExperience = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Language> languages = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<UserSkill> skills = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Award> awards = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<VolunteerExperience> volunteerExperience = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Certification> certifications = new HashSet<>();
}
