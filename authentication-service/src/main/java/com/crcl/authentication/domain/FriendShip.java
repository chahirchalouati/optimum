package com.crcl.authentication.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Document("friendShips")
@Data
@CompoundIndex(name = "owner_friends")
public class FriendShip extends BaseDocument {
    @Field(value = "owner")
    private User owner;
    @Field(value = "friends")
    private Set<User> friends = new HashSet<>();
}
