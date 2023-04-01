package com.crcl.post.service;

public interface NotificationService {

    void notifyCreatedPost(String destination, String targets, Object payload);
}
