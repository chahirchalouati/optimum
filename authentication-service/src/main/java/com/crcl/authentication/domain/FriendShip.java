package com.crcl.authentication.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Document("friendShips")
@Data
@CompoundIndex(name = "owner_friends")
public class FriendShip extends BaseDocument {
    @Field(value = "left")
    private User left;
    @Field(value = "right")
    private User right;
    @Field(value = "initiator")
    private String initiator;
    @Field(value = "state")
    private FriendShipState state;
}
