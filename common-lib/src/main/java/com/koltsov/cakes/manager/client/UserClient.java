package com.koltsov.cakes.manager.client;

import com.koltsov.cakes.manager.web.dto.user.UserCreateDto;
import com.koltsov.cakes.manager.web.dto.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "PEOPLE-SERVICE", path = "/api/v1/users")
public interface UserClient {

    @GetMapping
    Page<UserDto> getAllUsers(@RequestBody Pageable pageable);

    @GetMapping("{userId}")
    UserDto getUserById(@PathVariable("userId") Long userId);

    @PostMapping
    UserDto createUser(@RequestBody UserCreateDto userCreateDto);

    @PutMapping("{userId}")
    UserDto updateUserById(@PathVariable("userId") Long userId, @RequestBody UserDto UserDto);

    @DeleteMapping("{userId}")
    void deleteUserById(@PathVariable("userId") Long userId);
}
