package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document
public class Interest {
    @Id
    private String id;
    private String name;
    private InterestCategory category;

}
