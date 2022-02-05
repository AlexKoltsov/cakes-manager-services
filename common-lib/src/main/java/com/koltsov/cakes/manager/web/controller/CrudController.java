package com.koltsov.cakes.manager.web.controller;

import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class CrudController<T, ID, D, CD> {

    private final CrudService<T, ID> crudService;
    private final GenericMapper<T, D, CD> mapper;

    @GetMapping
    public Page<D> getPage(@ParameterObject Pageable pageable) {
        return crudService.getPage(pageable)
                .map(mapper::toDto);
    }

    @GetMapping("{id}")
    public D getById(@PathVariable ID id) {
        T found = crudService.getById(id);
        return mapper.toDto(found);
    }

    @PostMapping
    public D create(@RequestBody CD createDto) {
        T t = mapper.toNewEntity(createDto);
        T created = crudService.create(t);
        return mapper.toDto(created);
    }

    @PutMapping("{id}")
    public D update(@PathVariable ID id, @RequestBody D dto) {
        T t = mapper.toEntity(dto);
        T updated = crudService.update(id, t);
        return mapper.toDto(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable ID id) {
        crudService.delete(id);
    }
}
