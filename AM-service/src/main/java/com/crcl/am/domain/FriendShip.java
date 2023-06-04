package com.crcl.am.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Document("friendShips")
@Data
@CompoundIndex(name = "owner_friends")
public class FriendShip extends BaseDocument {
    @Field(value = "sender")
    private GramifyUser sender;
    @Field(value = "recipient")
    private GramifyUser recipient;
    @Field(value = "state")
    private FriendShipState state;
    private LocalDateTime acceptedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime deletedAt;

}
