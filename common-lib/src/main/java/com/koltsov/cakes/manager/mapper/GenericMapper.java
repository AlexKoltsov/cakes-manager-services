package com.koltsov.cakes.manager.mapper;

public interface GenericMapper<E, D, CD> {
    D toDto(E entity);

    E toEntity(D dto);

    E toNewEntity(CD createDto);
}
