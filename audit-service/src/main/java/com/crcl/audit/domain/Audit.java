package com.crcl.audit.domain;

import com.crcl.core.utils.AuditAction;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Document
@Data
@CompoundIndex(def = "{ title:1, publisher:1 }", background = true)
@Accessors(chain = true)
public class Audit {
    @Id
    private String id;

    @Field(name = "identifier")
    private String identifier;

    @Field(name = "publisher")
    private String publisher;

    @Field(name = "action")
    private AuditAction action;

    @Field(name = "details")
    private Map<String, Object> details = new HashMap<>();

    @Field(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Field(name = "createAt")
    private LocalDateTime createAt;

    @Field(name = "receivedAt")
    private LocalDateTime receivedAt;
}
