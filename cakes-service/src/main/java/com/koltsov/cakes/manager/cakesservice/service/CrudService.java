package com.koltsov.cakes.manager.cakesservice.service;


import java.util.List;

public interface CrudService<T, ID> {

    List<T> getAll();

    T getById(ID id);

    T create(T t);

    T update(ID id, T t);

    void delete(ID id);
}
