package com.crcl.post.domain;

import com.crcl.core.domain.Access;
import com.crcl.core.domain.PublishState;
import com.crcl.core.dto.ProfileDto;
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
    private boolean deleted = false;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }
}
