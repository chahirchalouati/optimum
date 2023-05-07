package com.crcl.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
public class FileUtils {

    public static void cleanUpFiles(String... paths) {
        Arrays.stream(paths).forEach(
                path -> {
                    try {
                        Files.delete(Paths.get(path).normalize().toAbsolutePath());
                        log.info("Deleted file: {}", path);
                    } catch (IOException e) {
                        log.error("Error deleting file: {} {} ", path, e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
