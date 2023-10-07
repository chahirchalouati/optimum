package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;


@Data
@Accessors(chain = true)
@Document(collection = "profiles")
public class Profile {
    @Id
    private String id;

    @Field(name = "username")
    @Indexed(unique = true,
            background = true,
            name = "username",
            direction = IndexDirection.DESCENDING)
    private String username;

    @Field(name = "avatar")
    private String avatar;

    @Field(name = "backgroundImage")
    private String backgroundImage;

    @Field(name = "occupation")
    private Occupation occupation;

    @Field(name = "hometown")
    private Hometown hometown;

    @Field(name = "location")
    private Set<Location> location = new HashSet<>();

    @Field(name = "interests")
    private Set<Interest> interests = new HashSet<>();

    @Field(name = "education")
    private Set<Education> education = new HashSet<>();

    @Field(name = "workExperience")
    private Set<WorkExperience> workExperience = new HashSet<>();

    @Field(name = "languages")
    private Set<Language> languages = new HashSet<>();

    @Field(name = "skills")
    private Set<UserSkill> skills = new HashSet<>();

    @Field(name = "awards")
    private Set<Award> awards = new HashSet<>();

    @Field(name = "volunteerExperience")
    private Set<VolunteerExperience> volunteerExperience = new HashSet<>();

    @Field(name = "certifications")
    private Set<Certification> certifications = new HashSet<>();

    @Field(name = "user")
    private com.crcl.core.dto.UserDto user;

    @Field("settings")
    private Settings settings;

}
