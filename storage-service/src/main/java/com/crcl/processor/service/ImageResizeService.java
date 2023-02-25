package com.crcl.processor.service;

import com.crcl.processor.dto.ResizeImageRequest;

public interface ImageResizeService {
    void resize(ResizeImageRequest request);
}
