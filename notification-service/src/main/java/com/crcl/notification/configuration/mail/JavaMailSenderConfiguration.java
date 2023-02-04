package com.crcl.notification.configuration.mail;

import com.crcl.notification.configuration.properties.JavaMailProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@AllArgsConstructor
public class JavaMailSenderConfiguration {
    private final JavaMailProperties javaMailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(javaMailProperties.getHost());
        javaMailSender.setPassword(javaMailProperties.getPassword());
        javaMailSender.setUsername(javaMailProperties.getUsername());
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", javaMailProperties.getProtocol());
        props.put("mail.smtp.auth", javaMailProperties.isAuth());
        props.put("mail.smtp.starttls.enable", javaMailProperties.isEnableTls());
        props.put("mail.debug", javaMailProperties.isDebug());
        return javaMailSender;
    }


}
