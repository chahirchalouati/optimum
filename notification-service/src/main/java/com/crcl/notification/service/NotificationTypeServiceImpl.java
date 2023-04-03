package com.crcl.notification.service;

import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.repository.NotificationTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationTypeServiceImpl implements NotificationTypeService {
    private final NotificationTypeRepository notificationTypeRepository;

    @Override
    public Mono<NotificationType> save(NotificationType notificationType) {
        return notificationTypeRepository.save(notificationType);
    }

    @Override
    public Flux<NotificationType> saveAll(List<NotificationType> entitiesDto) {
        return notificationTypeRepository.saveAll(entitiesDto);
    }

    @Override
    public Mono<Void> deleteById(String entityId) {
        return notificationTypeRepository.findById(entityId)
                .flatMap(notificationType -> notificationTypeRepository.deleteById(entityId))
                .switchIfEmpty(Mono.error(new RuntimeException("unable to find notificationType with id: " + entityId)));
    }

    @Override
    public Mono<NotificationType> findById(String entityId) {
        return notificationTypeRepository.findById(entityId)
                .switchIfEmpty(Mono.error(new RuntimeException("unable to find notificationType with id: " + entityId)));

    }

    @Override
    public Flux<NotificationType> findAll() {
        return Flux.just();
    }

    @Override
    public Flux<NotificationType> findAll(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable);
    }

    @Override
    public Mono<NotificationType> update(NotificationType notificationType, String entityId) {
        return notificationTypeRepository.findById(entityId)
                .flatMap(type -> notificationTypeRepository.save(notificationType))
                .switchIfEmpty(Mono.error(new RuntimeException("unable to find notificationType with id: " + entityId)));

    }
}
