package com.crcl.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

    @Test
    void testCleanUpFiles() throws IOException {
        // create temporary files to delete
        Path tempFile1 = Files.createTempFile("test1", ".txt");
        Path tempFile2 = Files.createTempFile("test2", ".txt");

        // call the method to be tested
        FileUtils.cleanUpFiles(tempFile1.toString(), tempFile2.toString());

        // assert that the files were deleted
        assertFalse(Files.exists(tempFile1));
        assertFalse(Files.exists(tempFile2));
    }
}
