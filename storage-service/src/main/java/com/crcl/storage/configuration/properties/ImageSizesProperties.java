package com.crcl.storage.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "image-sizes")
public class ImageSizesProperties {
    private Map<String, ImageSize> sizes = new HashMap<>();

    @Data
    public static class ImageSize {
        private int width;
        private int height;

        public String getLabel() {
            return "_" + this.width + "_" + this.height;
        }
    }
}
