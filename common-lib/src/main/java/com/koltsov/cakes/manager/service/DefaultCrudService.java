package com.koltsov.cakes.manager.service;

import com.koltsov.cakes.manager.data.IdAble;
import com.koltsov.cakes.manager.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
public abstract class DefaultCrudService<T extends IdAble<ID>, ID> implements CrudService<T, ID> {

    private final JpaRepository<T, ID> repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T getById(ID id) {
        return realGetById(id);
    }

    @Override
    public T create(T t) {
        return repository.save(t);
    }

    @Override
    public T update(ID id, T t) {
        if (!id.equals(t.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be the same in path and in body");
        }
        T found = realGetById(t.getId());
        updateFields(found, t);
        return repository.save(found);    }

    @Override
    public void delete(ID id) {
        T found = realGetById(id);
        repository.delete(found);
    }

    private T realGetById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString()));
    }

    protected abstract void updateFields(T to, T from);
}
