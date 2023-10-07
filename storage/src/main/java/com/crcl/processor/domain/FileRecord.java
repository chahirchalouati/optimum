package com.crcl.processor.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Document
@Data
@Accessors(chain = true)
public class FileRecord {
    @Id
    private String id;
    private String owner;
    private String name;
    private String bucket;
    private String tag;
    private String version;
    private String type;
    private String url;
    private Set<String> records = new HashSet<>();
    private Map<String, String> tags = new HashMap<>();
}
