package com.crcl.common.queue;

import com.crcl.common.dto.responses.FileUploadResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageResizeEvent extends BaseEvent {
    private String id;
    private FileUploadResult result;

}
