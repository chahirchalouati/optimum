package com.crcl.core.dto.queue;

import com.crcl.core.dto.AccessDto;
import com.crcl.core.dto.File;
import com.crcl.core.dto.TagDto;

import java.util.List;

public record CreatePostPayload(
        List<File> files,
        String content,
        AccessDto access,
        List<String> sharedWithUsers,
        List<TagDto> tags,
        String location) {
}
