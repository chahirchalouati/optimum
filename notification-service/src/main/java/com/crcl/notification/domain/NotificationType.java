package com.crcl.notification.domain;

import com.crcl.common.utils.NotificationTargets;
import lombok.Data;
import lombok.experimental.Accessors;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Accessors(chain = true)
public class NotificationType {
    @Id
    private String id;
    @Indexed(unique = true)
    private String type;
    private boolean enabled = false;
    private boolean async = false;
    private boolean email = false;
    private boolean push = false;
    private boolean sms = false;
    private NotificationTargets targets;
}
