package com.crcl.post.domain;

import com.crcl.core.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Accessors(chain = true)
@CompoundIndexes(
        @CompoundIndex(
                background = true,
                unique = true,
                def = """
                         {
                         "entityId" : 1,
                         "user.username": 1,
                         "sharedWith.username": 1
                         }
                        """
        )
)
public class Share {
    @Id
    private String id;
    @NotNull
    private String entityId;
    private UserDto sharedWith;
    @CreatedBy
    private UserDto user;
    @CreatedDate
    private LocalDateTime createdAt;

    public Share(String entityId, UserDto sharedWith) {
        this.entityId = entityId;
        this.sharedWith = sharedWith;
    }
}
