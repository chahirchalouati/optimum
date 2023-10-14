package com.crcl.friend_ship.dto;

import io.minio.ObjectWriteResponse;
import lombok.Data;

import java.util.Map;

@Data
public class WriteResponse {
    private ObjectWriteResponse writeResponse;
    private Map<String, String> tags;

    public WriteResponse(ObjectWriteResponse writeResponse, Map<String, String> tags) {
        this.writeResponse = writeResponse;
        this.tags = tags;
    }
}
