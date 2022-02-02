package com.koltsov.cakes.manager.cakesservice.service;

import com.koltsov.cakes.manager.cakesservice.data.Cake;
import com.koltsov.cakes.manager.exceptions.NotFoundException;
import com.koltsov.cakes.manager.cakesservice.repository.CakeRepository;
import com.koltsov.cakes.manager.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CakeService implements CrudService<Cake, Long> {

    private final CakeRepository cakeRepository;

    @Override
    public List<Cake> getAll() {
        return cakeRepository.findAll();
    }

    @Override
    public Cake getById(Long id) {
        return realGetById(id);
    }

    @Override
    public Cake create(Cake cake) {
        return cakeRepository.save(cake);
    }

    @Override
    public Cake update(Long id, Cake cake) {
        if (!id.equals(cake.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be the same in path and in body");
        }
        Cake found = realGetById(cake.getId());
        found.setName(cake.getName());
        return cakeRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        Cake found = realGetById(id);
        cakeRepository.deleteById(found.getId());
    }

    private Cake realGetById(Long id) {
        return cakeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }
}
