package com.crcl.post.domain;

import com.crcl.core.domain.TagKind;
import com.crcl.core.dto.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Accessors(chain = true)
public class Tag {
    @Indexed(unique = true)
    private String value;
    private String entityId;
    private boolean system = false;
    private TagKind kind;
    @CreatedBy
    private UserDto userDto;
    @CreatedDate
    private LocalDateTime createdAt;

}
