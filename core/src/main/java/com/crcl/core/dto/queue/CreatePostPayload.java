package com.crcl.core.dto.queue;

import com.crcl.core.dto.AccessDto;
import com.crcl.core.dto.TagDto;
import com.crcl.core.dto.responses.FileUploadResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CreatePostPayload {
    private List<FileUploadResult> files = new ArrayList<>();
    private String content;
    private AccessDto access;
    private List<String> sharedWithUsers;
    private List<TagDto> tags;
    private String location;
}
