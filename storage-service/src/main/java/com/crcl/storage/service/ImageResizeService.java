package com.crcl.storage.service;

import com.crcl.storage.dto.ResizeImageRequest;

public interface ImageResizeService {
    void resize(ResizeImageRequest request);
}
