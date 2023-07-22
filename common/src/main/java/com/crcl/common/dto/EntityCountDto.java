package com.crcl.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
public class EntityCountDto {
    private String id;
    private long count;

    public EntityCountDto(String id, long count) {
        this.id = id;
        this.count = count;
    }
}
