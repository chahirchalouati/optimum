package com.crcl.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GenericFile {
    protected Integer index;
    protected String id;
    protected String parent;
    protected String url;
    protected String contentType;

    public GenericFile(Integer index) {
        this.index = index;
    }
}
