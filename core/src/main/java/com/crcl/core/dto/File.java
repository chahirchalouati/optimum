package com.crcl.core.dto;

public record File(String name, String contentType, byte[] content) {
}
