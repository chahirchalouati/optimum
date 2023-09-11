package com.crcl.authentication.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("roles")
@Data
@Accessors(chain = true)
public class Role {
    @Id
    private String id;
    private boolean enabled = true;
    private String name;
    private Set<GramifyPermission> permissions = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

}
