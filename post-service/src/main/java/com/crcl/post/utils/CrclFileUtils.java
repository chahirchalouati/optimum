package com.crcl.post.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Objects.nonNull;

public class CrclFileUtils {

    public static boolean hasFiles(List<MultipartFile> files) {
        return nonNull(files) && !files.stream()
                .map(MultipartFile::getOriginalFilename)
                .toList()
                .isEmpty();
    }
}
