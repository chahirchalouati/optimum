package com.crcl.authenticationservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("roles")
@Data
public class Role {
    @Id
    private String id;
    private boolean enabled = true;
    private String name;
    private Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

}
