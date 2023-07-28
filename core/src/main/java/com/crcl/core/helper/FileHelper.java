package com.crcl.core.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j

public class FileHelper {

    private final ResourceLoader resourceLoader;

    public String loadFileAsString(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readResourceToString(resource);
        } catch (IOException e) {
            log.error("Error when loading file as string: {}", filePath, e);
            return null;
        }
    }

    public byte[] loadFileAsBytes(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readResourceToBytes(resource);
        } catch (IOException e) {
            log.error("Error when loading file as bytes: {}", filePath, e);
            return null;
        }
    }

    public String loadBufferedFileAsString(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readBufferedResourceToString(resource);
        } catch (IOException e) {
            log.error("Error when loading buffered file as string: {}", filePath, e);
            return null;
        }
    }

    public byte[] loadBufferedFileAsBytes(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readBufferedResourceToBytes(resource);
        } catch (IOException e) {
            log.error("Error when loading buffered file as bytes: {}", filePath, e);
            return null;
        }
    }

    private Resource getResource(String filePath) {
        return resourceLoader.getResource("classpath:" + filePath);
    }

    private String readResourceToString(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] fileBytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(fileBytes, StandardCharsets.UTF_8);
        }
    }

    private byte[] readResourceToBytes(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return FileCopyUtils.copyToByteArray(inputStream);
        }
    }

    private String readBufferedResourceToString(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] fileBytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(fileBytes, StandardCharsets.UTF_8);
        }
    }

    private byte[] readBufferedResourceToBytes(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return FileCopyUtils.copyToByteArray(inputStream);
        }
    }

}
