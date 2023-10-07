package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document
public class Language {
    private String name;
    private Proficiency proficiency;
}
