package com.crcl.post.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CrclFileUtils {
    public static boolean hasFiles(List<MultipartFile> files) {
        var strings = files.stream()
                .map(MultipartFile::getOriginalFilename)
                .toList();
        return !strings.isEmpty();
    }
}
