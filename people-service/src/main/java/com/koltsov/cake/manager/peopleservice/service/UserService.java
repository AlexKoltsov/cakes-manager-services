package com.koltsov.cake.manager.peopleservice.service;

import com.koltsov.cake.manager.peopleservice.data.User;
import com.koltsov.cake.manager.peopleservice.repository.UserRepository;
import com.koltsov.cakes.manager.exceptions.NotFoundException;
import com.koltsov.cakes.manager.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements CrudService<User, Long> {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return realGetById(id);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        if (!id.equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be the same in path and in body");
        }
        User found = realGetById(user.getId());
        updateFields(found, user);
        return userRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        User found = realGetById(id);
        userRepository.deleteById(found.getId());
    }

    private User realGetById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    private void updateFields(User to, User from) {
        to.setLogin(from.getLogin());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setBirthDate(from.getBirthDate());
        to.setEmail(from.getEmail());
    }
}
