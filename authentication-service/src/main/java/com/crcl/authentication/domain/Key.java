package com.crcl.authentication.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
@Data
@Accessors(chain = true)
public class Key {
    @Id
    private String id;
    private Map<String, Object> key = new HashMap<>();
    private boolean isValid = false;
}
