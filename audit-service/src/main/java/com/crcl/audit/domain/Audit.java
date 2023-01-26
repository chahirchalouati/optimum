package com.crcl.audit.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
public class Audit {
    @Field(name = "id")
    @Id
    private String id;
    @Field(name = "title")
    private String title;
    @Field(name = "publisher")
    private String publisher;
}
