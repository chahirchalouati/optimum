package com.crcl.notification.service;

import com.crcl.core.utils.generic.ReactiveGenericService;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

public interface NotificationTypeService extends ReactiveGenericService<NotificationType, String> {
    Mono<Page<NotificationType>> search(NotificationTypeRequest request);
}
