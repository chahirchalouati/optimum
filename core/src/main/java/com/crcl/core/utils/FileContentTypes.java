package com.crcl.core.utils;

import java.util.Set;

public interface FileContentTypes {
    // Video formats
    String VIDEO_AVI = "video/avi";
    String VIDEO_FLV = "video/x-flv";
    String VIDEO_MP4 = "video/mp4";
    String VIDEO_MPEG = "video/mpeg";
    String VIDEO_WEBM = "video/webm";
    String VIDEO_3GPP = "video/3gpp";
    String VIDEO_QUICKTIME = "video/quicktime";
    String VIDEO_MKV = "video/x-matroska";
    String VIDEO_OGG = "video/ogg";

    // Image formats
    String IMAGE_JPEG = "image/jpeg";
    String IMAGE_PNG = "image/png";
    String IMAGE_GIF = "image/gif";
    String IMAGE_BMP = "image/bmp";
    String IMAGE_WEBP = "image/webp";
    String IMAGE_SVG = "image/svg+xml";
    String IMAGE_TIFF = "image/tiff";
    String IMAGE_ICO = "image/x-icon";
    String IMAGE_HEIF = "image/heif";
    String IMAGE_HEIC = "image/heic";
    String IMAGE_RAW = "image/x-panasonic-raw";
    String IMAGE_JPG = "image/jpg";

    // Audio formats
    String AUDIO_MPEG = "audio/mpeg";
    String AUDIO_WAV = "audio/wav";
    String AUDIO_OGG = "audio/ogg";
    String AUDIO_WEBM = "audio/webm";
    String AUDIO_AAC = "audio/aac";
    String AUDIO_FLAC = "audio/flac";
    String AUDIO_ALAC = "audio/alac";
    String AUDIO_AMR = "audio/amr";
    String AUDIO_OPUS = "audio/opus";
    String AUDIO_MIDI = "audio/midi";

    Set<String> AUDIO_FORMATS = Set.of(
            AUDIO_MPEG, AUDIO_WAV, AUDIO_OGG, AUDIO_WEBM,
            AUDIO_AAC, AUDIO_FLAC, AUDIO_ALAC, AUDIO_AMR,
            AUDIO_OPUS, AUDIO_MIDI
    );
    Set<String> VIDEO_FORMATS = Set.of(
            VIDEO_AVI, VIDEO_FLV, VIDEO_MP4, VIDEO_MPEG, VIDEO_WEBM,
            VIDEO_3GPP, VIDEO_QUICKTIME, VIDEO_MKV, VIDEO_OGG
    );

    Set<String> IMAGE_FORMATS = Set.of(
            IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF, IMAGE_BMP, IMAGE_WEBP,
            IMAGE_SVG, IMAGE_TIFF, IMAGE_ICO, IMAGE_HEIF, IMAGE_HEIC,
            IMAGE_RAW, IMAGE_JPG
    );
}
