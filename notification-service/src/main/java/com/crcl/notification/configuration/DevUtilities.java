package com.crcl.notification.configuration;

import com.crcl.common.utils.NotificationDefinition;
import com.crcl.common.utils.NotificationTargets;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.Template;
import com.crcl.notification.repository.NotificationTypeRepository;
import com.crcl.notification.repository.TemplateRepository;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class DevUtilities {

    @Bean
    CommandLineRunner runnerNotificationTypeRepository(NotificationTypeRepository notificationTypeRepository, TemplateRepository templateRepository) {
        notificationTypeRepository.deleteAll().subscribe();
        String style = """
                    .home{
                    background: white;
                    }
                    """;
        String content = """
                <!DOCTYPE html>
                <html xmlns:th="http://www.thymeleaf.org">
                <head>
                    <title>Email</title>
                </head>
                <body>
                    <p>Dear <span th:text="${id}">Name</span>,</p>
                    <p>Thank you for your recent purchase from our store. Your order number is 
                    <span th:text="${orderNumber}">Order Number</span>.</p>
                    <p>We hope you enjoy your purchase and look forward to serving you again in the future.</p>
                    <p>Best regards,</p>
                    <p>Your friends at the store</p>
                </body>
                </html>                            
                """;            Template template = new Template();
        template.setContent(content);
        template.setStyle(style);
        Template template1 = templateRepository.save(template).block();

        return args -> {
            NotificationTargets[] values = NotificationTargets.values();
            List<NotificationType> notificationTypes = Arrays.stream(NotificationDefinition.values())
                    .map(type -> new NotificationType()
                            .setType(type)
                            .setEnabled(true)
                            .setAsync(false)
                            .setEmail(true)
                            .setPush(false)
                            .setSms(false)
                            .setTemplateId(template1.getId())
                            .setNotificationTargets(values[RandomUtils.nextInt(0, values.length - 1)]))
                    .toList();
            notificationTypeRepository.saveAll(notificationTypes).subscribe();
        };
    }

    @Bean
    CommandLineRunner runnerTemplateRepository(TemplateRepository templateRepository) {

        return args -> {

        };
    }
}
