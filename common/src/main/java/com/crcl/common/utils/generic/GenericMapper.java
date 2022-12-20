package com.crcl.common.utils.generic;

import java.util.List;

public interface GenericMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> mapToEntity(List<D> dtoList);

    List<D> mapToDto(List<E> entityList);

}
