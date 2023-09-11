package com.crcl.post.domain;

import com.crcl.common.domain.PublishState;
import com.crcl.core.dto.ProfileDto;
import com.crcl.core.dto.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document
@Data
@Accessors(chain = true)
public class Post {
    @Id
    private String id;
    private String content;
    private ProfileDto creator;
    private Access access = Access.PUBLIC;
    private PublishState publishState = PublishState.IN_PROGRESS;
    private Set<Video> videos = new HashSet<>();
    private Set<Image> images = new HashSet<>();
    private Set<GenericFile> genericFiles = new HashSet<>();
    private Set<Tag> tags = new HashSet<>();
    private Set<Like> likes = new HashSet<>();
    private Set<UserDto> sharedWithUsers = new HashSet<>();
    private boolean deleted = false;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
