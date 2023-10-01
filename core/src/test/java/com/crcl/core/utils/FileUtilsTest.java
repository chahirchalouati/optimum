package com.crcl.core.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @Test
    void zip() throws IOException {
        // create temporary files to delete
        Path tempFile1 = FileUtils.createFileInDirectory("test1", "file.txt");

        FileUtils.zip(Files.newInputStream(tempFile1), new ByteArrayOutputStream());

    }
}
