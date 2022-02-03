package com.koltsov.cakes.manager.client;

import com.koltsov.cakes.manager.web.dto.cake.CakeCreateDto;
import com.koltsov.cakes.manager.web.dto.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "users", url = "http://localhost:8081", path = "/api/v1/users")
public interface UserClient {

    @GetMapping
    List<UserDto> getAllUsers();

    @GetMapping("{userId}")
    UserDto getUserById(@PathVariable("userId") Long userId);

    @PostMapping
    UserDto createUser(@RequestBody CakeCreateDto cakeCreateDto);

    @PutMapping("{userId}")
    UserDto updateUserById(@PathVariable("userId") Long userId, @RequestBody UserDto UserDto);

    @DeleteMapping("{userId}")
    void deleteUserById(@PathVariable("userId") Long userId);
}
