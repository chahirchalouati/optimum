package com.crcl.friend_ship.configuration.properties;

import com.crcl.core.properties.ImageSize;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "image-sizes")
public class ImageSizesProperties {
    private Map<String, ImageSize> sizes = new HashMap<>();


}
