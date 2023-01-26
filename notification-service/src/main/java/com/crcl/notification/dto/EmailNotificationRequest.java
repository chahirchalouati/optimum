package com.crcl.notification.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmailNotificationRequest extends NotificationRequest {
    private String email;
}
