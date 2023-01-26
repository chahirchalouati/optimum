package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Accessors(chain = true)
@Document(collection = "profiles")
public class Profile {
    @Id
    private String id;
    private String username;
    private String email;
    private String avatar;
    private String backgroundImage;
    @Field("user")
    private UserDto user;


}
