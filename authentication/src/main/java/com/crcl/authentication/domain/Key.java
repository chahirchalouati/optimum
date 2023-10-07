package com.crcl.authentication.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "keys")
@Data
@Accessors(chain = true)
public class Key {
    @Id
    private String id;
    @Field("key")
    private Map<String, Object> value = new HashMap<>();
    @Field("valid")
    private boolean isValid = false;
    @CreatedDate
    private LocalDateTime createdAt;
}
