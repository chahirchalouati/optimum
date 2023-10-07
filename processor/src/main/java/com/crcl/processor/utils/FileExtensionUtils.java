package com.crcl.processor.utils;

import com.crcl.core.dto.responses.FileUploadResult;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.function.Predicate;


public class FileExtensionUtils {

    public static boolean isImage(String fileName) {
        // Get the MIME type of the file based on its filename
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimeType = fileNameMap.getContentTypeFor(fileName);
        // Check if the MIME type indicates that the file is an image
        return mimeType != null && mimeType.startsWith("image/");
    }

    public static boolean isVideo(String fileName) {
        // Get the MIME type of the file based on its filename
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimeType = fileNameMap.getContentTypeFor(fileName);
        // Check if the MIME type indicates that the file is an image
        return mimeType != null && mimeType.startsWith("video/");
    }

    public static Predicate<FileUploadResult> isVideo() {
        return result -> FileExtensionUtils.isVideo(result.getName());
    }

    public static Predicate<FileUploadResult> isImage() {
        return result -> FileExtensionUtils.isImage(result.getName());
    }


}
