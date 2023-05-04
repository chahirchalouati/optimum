package com.crcl.common.utils.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReactiveGenericService<EntityDto, EntityId> {
    Mono<EntityDto> save(EntityDto entityDto);

    Flux<EntityDto> saveAll(List<EntityDto> entitiesDto);

    Mono<Void> deleteById(EntityId entityId);

    Mono<EntityDto> findById(EntityId entityId);

    Flux<EntityDto> findAll();

    Mono<Page<EntityDto>> findAll(Pageable pageable);

    Mono<EntityDto> update(EntityDto entityDto, EntityId entityId);
}
