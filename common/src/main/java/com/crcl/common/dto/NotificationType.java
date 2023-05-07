package com.crcl.common.dto;

import com.crcl.common.utils.NotificationTargets;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NotificationType {
    private String id;
    private String type;
    private boolean async = false;
    private boolean email = false;
    private boolean push = false;
    private boolean sms = false;
    private NotificationTargets targets;
}
