package com.crcl.notification.controller;


import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import com.crcl.notification.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("notification-types")
@RequiredArgsConstructor
public class NotificationTypeController {

    private final NotificationTypeService notificationTypeService;

    @PostMapping
    public Mono<NotificationType> save(NotificationType type) {
        return notificationTypeService.save(type);
    }

    @PostMapping("/many")
    public Flux<NotificationType> saveAll(List<NotificationType> entitiesDto) {
        return notificationTypeService.saveAll(entitiesDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
        return notificationTypeService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Mono<NotificationType> findById(@PathVariable String id) {
        return notificationTypeService.findById(id);
    }

    @GetMapping("/search")
    public Mono<Page<NotificationType>> findByType(NotificationTypeRequest type) {
        return notificationTypeService.search(type);
    }

    @GetMapping
    public Mono<Page<NotificationType>> findAll(Pageable pageable) {
        return notificationTypeService.findAll(pageable);
    }

    @PutMapping("/{id}")
    public Mono<NotificationType> update(NotificationType type, @PathVariable String id) {
        return notificationTypeService.update(type, id);
    }
}
