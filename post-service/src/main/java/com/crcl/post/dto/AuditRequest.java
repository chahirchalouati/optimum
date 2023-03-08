package com.crcl.post.dto;

import com.crcl.post.domain.Common;
import lombok.Data;

@Data
public class AuditRequest {
    protected String username;
    protected Common.Visibility visibility;
    private Long id;
    private Integer attachmentsSize;
    private Integer tagsSize;
    private Integer likesSize;
}
