package com.koltsov.cakes.manager.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> getAll();

    Page<T> getPage(Pageable pageable);

    T getById(ID id);

    T create(T t);

    T update(ID id, T t);

    void delete(ID id);
}
