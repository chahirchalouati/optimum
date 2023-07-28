package com.crcl.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GenericFileDto {
    protected Integer index;
    protected String id;
    protected String parent;
    protected String url;
    protected String contentType;

    public GenericFileDto(Integer index) {
        this.index = index;
    }
}
