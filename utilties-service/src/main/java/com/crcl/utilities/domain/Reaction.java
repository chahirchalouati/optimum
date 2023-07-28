package com.crcl.utilities.domain;

import com.crcl.core.dto.Entity;
import com.crcl.core.dto.UserDto;
import com.crcl.core.dto.requests.ReactionType;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@FieldNameConstants
@Accessors(chain = true)
@CompoundIndexes(
        @CompoundIndex(
                unique = true,
                background = true,
                name = "reaction_entity_target_user_type_idx",
                def = """ 
                        {
                        "entity": 1,
                        "targetId": 1,
                        "reactingUser.username": 1,
                        "reactingUser.email": 1,
                        "type": 1,
                        "deleted":1
                        }
                                        """)
)
public class Reaction {
    @Id
    private String id;
    private Entity entity;
    private String targetId;
    private UserDto reactingUser;
    private ReactionType type;
    private boolean deleted = false;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
