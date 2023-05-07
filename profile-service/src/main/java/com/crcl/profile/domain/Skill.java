package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Document
public class Skill {
    @NotBlank
    @Indexed(unique = true, background = true, direction = IndexDirection.DESCENDING)
    private String name;
}




