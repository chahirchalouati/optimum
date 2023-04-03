package com.crcl.notification.service;

import com.crcl.common.utils.generic.ReactiveGenericService;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import reactor.core.publisher.Mono;

public interface NotificationTypeService extends ReactiveGenericService<NotificationType, String> {
    Mono<NotificationType> search(NotificationTypeRequest request);
}
