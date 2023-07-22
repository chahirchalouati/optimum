package com.crcl.reaction.domain;

import com.crcl.common.dto.UserDto;
import com.crcl.common.dto.requests.ReactionType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Reaction {
    @Id
    private String id;
    private UserDto reactingUser;
    private ReactionType type;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
