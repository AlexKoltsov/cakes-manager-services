package com.koltsov.cakes.manager.cakesservice.service;

import com.koltsov.cakes.manager.cakesservice.data.Cake;
import com.koltsov.cakes.manager.service.DefaultCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CakeService extends DefaultCrudService<Cake, Long> {

    public CakeService(JpaRepository<Cake, Long> repository) {
        super(repository);
    }

    @Override
    protected void updateFields(Cake to, Cake from) {
        to.setName(from.getName());
    }
}
