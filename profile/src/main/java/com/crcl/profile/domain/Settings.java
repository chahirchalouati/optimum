package com.crcl.profile.domain;

import com.crcl.core.utils.NotificationDefinition;
import com.crcl.core.utils.NotificationTargets;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class Settings {

    private NotificationSettings notificationSettings;

    @Data
    public static class NotificationSettings {
        private Map<NotificationDefinition, NotificationTargets> targetsSettings;
    }
}
