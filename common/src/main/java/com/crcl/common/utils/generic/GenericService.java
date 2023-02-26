package com.crcl.common.utils.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<EntityDto, EntityId> {
    EntityDto saveAll(EntityDto entityDto);

    List<EntityDto> saveAll(List<EntityDto> entitiesDto);

    void deleteById(EntityId entityId);

    EntityDto findById(EntityId entityId);

    List<EntityDto> findAll();

    Page<EntityDto> findAll(Pageable pageable);

    EntityDto update(EntityDto entityDto, EntityId entityId);
}
