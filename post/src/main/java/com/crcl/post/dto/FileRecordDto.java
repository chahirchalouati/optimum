package com.crcl.post.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
public class FileRecordDto {

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
