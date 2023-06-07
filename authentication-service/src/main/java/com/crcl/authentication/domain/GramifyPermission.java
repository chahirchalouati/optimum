package com.crcl.authentication.domain;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("permissions")
@Data
public class GramifyPermission {
    @Id
    private String id;
    @NotBlank
    private String name;
    private boolean enabled;

    public GramifyPermission(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
