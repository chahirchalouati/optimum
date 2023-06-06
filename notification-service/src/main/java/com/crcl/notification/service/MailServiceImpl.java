package com.crcl.notification.service;

import com.crcl.notification.configuration.properties.JavaMailProperties;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    protected final JavaMailSender mailSender;
    protected final JavaMailProperties javaMailProperties;

    @Override
    public void send(String content, String[] to, String subject) {
        send(content, to, null, subject);
    }

    @Override
    public void send(String content, String[] to, String[] cc, String subject) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(to);
            helper.setFrom(javaMailProperties.getUsername());
            helper.setSubject(subject);
            helper.setText(content, true);
            if (Objects.nonNull(cc) && cc.length > 0) {
                helper.setCc(cc);
            }

            mailSender.send(message);
            for (String destination : to) {
                log.info("end sending mail for " + destination);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
