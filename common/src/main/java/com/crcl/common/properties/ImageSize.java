package com.crcl.common.properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ImageSize {
    private int width;
    private int height;
    private String name;

    public String getLabel() {
        return "_" + this.width + "_" + this.height;
    }
}
