package com.crcl.post.utils;

import java.net.FileNameMap;
import java.net.URLConnection;


public class FileExtensionUtils {

    public static boolean isImage(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimeType = fileNameMap.getContentTypeFor(fileName);
        return mimeType != null && mimeType.startsWith("image/");
    }

    public static boolean isVideo(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimeType = fileNameMap.getContentTypeFor(fileName);
        return mimeType != null && mimeType.startsWith("video/");
    }
}
