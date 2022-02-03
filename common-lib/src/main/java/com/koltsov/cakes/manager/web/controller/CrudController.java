package com.koltsov.cakes.manager.web.controller;

import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class CrudController<T, ID, D, CD> {

    private final CrudService<T, ID> crudService;
    private final GenericMapper<T, D, CD> mapper;

    @GetMapping
    public List<D> getAll() {
        return crudService.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
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
