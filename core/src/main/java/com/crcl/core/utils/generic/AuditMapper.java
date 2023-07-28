package com.crcl.core.utils.generic;

import java.util.List;
import java.util.Optional;

public interface AuditMapper<E, D> {
    E toObject(D dto);

    D toAudit(E entity);

    List<E> mapToObject(List<D> dtoList);

    List<D> mapToAudit(List<E> entityList);

    default Optional<E> toOptionalObject(D dto) {
        return Optional.ofNullable(this.toObject(dto));
    }

    default Optional<D> toOptionalAudit(E entity) {
        return Optional.ofNullable(this.toAudit(entity));
    }
}
