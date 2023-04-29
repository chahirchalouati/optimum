package com.crcl.common.dto.queue;

import com.crcl.common.dto.responses.FileUploadResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageResize extends Auditable {
    private String id;
    private FileUploadResult result;

}
