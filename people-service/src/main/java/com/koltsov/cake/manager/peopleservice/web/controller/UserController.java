package com.koltsov.cake.manager.peopleservice.web.controller;

import com.koltsov.cake.manager.peopleservice.data.User;
import com.koltsov.cakes.manager.web.dto.user.UserCreateDto;
import com.koltsov.cakes.manager.web.dto.user.UserDto;
import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.service.CrudService;
import com.koltsov.cakes.manager.web.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends CrudController<User, Long, UserDto, UserCreateDto> {

    public UserController(CrudService<User, Long> crudService,
                          GenericMapper<User, UserDto, UserCreateDto> mapper) {
        super(crudService, mapper);
    }
}
