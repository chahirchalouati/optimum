package com.crcl.notification.service;

public interface MailService {
    void send(String content, String[] to, String subject);

    void send(String content, String[] to, String[] cc, String subject);
}
