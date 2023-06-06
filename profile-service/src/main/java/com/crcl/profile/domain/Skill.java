package com.crcl.profile.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document
public class Skill {
    @NotBlank
    @Indexed(unique = true, background = true, direction = IndexDirection.DESCENDING)
    private String name;
}




