package com.crcl.post.configuration.properties;

import com.crcl.common.properties.ImageSize;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "image-sizes")
public class ImageSizesProperties {
    private Map<String, ImageSize> sizes = new HashMap<>();


}
