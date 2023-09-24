package com.crcl.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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

    public static void zip(InputStream sourceInputStream, OutputStream zipOutputStream) {
        try (ZipOutputStream zos = new ZipOutputStream(zipOutputStream)) {
            ZipEntry zipEntry = new ZipEntry("file.txt");
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = sourceInputStream.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }

            zos.closeEntry();
            log.debug("File zipped successfully.");

        } catch (IOException e) {
            log.error("Error when zipping file: {}", e.getMessage());
        }
    }

    public static void unzip(InputStream zipInputStream, OutputStream unzipOutputStream) {
        try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
            ZipEntry zipEntry = zis.getNextEntry();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while (zipEntry != null) {
                while ((bytesRead = zis.read(buffer)) != -1) {
                    unzipOutputStream.write(buffer, 0, bytesRead);
                }

                zipEntry = zis.getNextEntry();
            }

            log.debug("File unzipped successfully.");

        } catch (IOException e) {
            log.error("Error when unzipping file: {}", e.getMessage());
        }
    }
}
