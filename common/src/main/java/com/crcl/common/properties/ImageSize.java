package com.crcl.common.properties;

import lombok.Data;

@Data
public class ImageSize {
    private int width;
    private int height;
    private String name;

    public String getLabel() {
        return "_" + this.width + "_" + this.height;
    }
}
