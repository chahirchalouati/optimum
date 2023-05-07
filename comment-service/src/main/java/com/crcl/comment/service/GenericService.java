package com.crcl.comment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<D, I> {
    D save(D d);

    List<D> saveAll(List<D> entities);

    void deleteById(I i);

    D findById(I i);

    List<D> findAll();

    Page<D> findAll(Pageable pageable);

    D update(D e, I i);
}
