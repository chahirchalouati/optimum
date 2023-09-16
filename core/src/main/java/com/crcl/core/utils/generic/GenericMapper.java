package com.crcl.core.utils.generic;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface GenericMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> mapToEntity(List<D> dtoList);

    List<D> mapToDto(List<E> entityList);

    default Optional<E> toOptionalEntity(D dto) {
        return Optional.ofNullable(this.toEntity(dto));
    }

    default Optional<D> toOptionalDto(E entity) {
        return Optional.ofNullable(this.toDto(entity));
    }

    default Function<D, E> fnToEntity() {
        return this::toEntity;
    }
    default Function<E, D> fnToDto() {
        return this::toDto;
    }
}
