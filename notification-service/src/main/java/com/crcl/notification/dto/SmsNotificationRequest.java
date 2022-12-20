package com.crcl.notification.dto;

import com.crcl.common.domain.AppUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SmsNotificationRequest extends NotificationRequest {
    private AppUser.Phone phone;
}
