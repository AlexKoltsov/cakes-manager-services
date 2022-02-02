package com.koltsov.cake.manager.peopleservice.web.controller;

import com.koltsov.cake.manager.peopleservice.data.User;
import com.koltsov.cake.manager.peopleservice.mapper.UserMapper;
import com.koltsov.cake.manager.peopleservice.service.UserService;
import com.koltsov.cake.manager.peopleservice.web.dto.UserCreateDto;
import com.koltsov.cake.manager.peopleservice.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @PostMapping
    public UserDto create(@RequestBody UserCreateDto userCreateDto) {
        User cake = userMapper.toNewEntity(userCreateDto);
        User created = userService.create(cake);
        return userMapper.toDto(created);
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto) {
        User cake = userMapper.toEntity(userDto);
        User updated = userService.update(id, cake);
        return userMapper.toDto(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
