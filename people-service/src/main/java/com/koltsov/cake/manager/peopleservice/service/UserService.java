package com.koltsov.cake.manager.peopleservice.service;

import com.koltsov.cake.manager.peopleservice.data.User;
import com.koltsov.cakes.manager.service.DefaultCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DefaultCrudService<User, Long> {

    public UserService(JpaRepository<User, Long> repository) {
        super(repository);
    }

    protected void updateFields(User to, User from) {
        to.setLogin(from.getLogin());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setBirthDate(from.getBirthDate());
        to.setEmail(from.getEmail());
    }
}
