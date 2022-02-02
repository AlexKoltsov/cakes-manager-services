package com.koltsov.cakes.manager.cakesservice.web.controller;

import com.koltsov.cakes.manager.cakesservice.data.Cake;
import com.koltsov.cakes.manager.cakesservice.mapper.CakeMapper;
import com.koltsov.cakes.manager.cakesservice.service.CakeService;
import com.koltsov.cakes.manager.cakesservice.web.dto.CakeCreateDto;
import com.koltsov.cakes.manager.cakesservice.web.dto.CakeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cakes")
public class CakeController {

    private final CakeService cakeService;
    private final CakeMapper cakeMapper;

    @GetMapping
    public List<CakeDto> getAll() {
        return cakeService.getAll().stream()
                .map(cakeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CakeDto getById(@PathVariable Long id) {
        Cake cake = cakeService.getById(id);
        return cakeMapper.toDto(cake);
    }

    @PostMapping
    public CakeDto create(@RequestBody CakeCreateDto cakeCreateDto) {
        Cake cake = cakeMapper.toNewEntity(cakeCreateDto);
        Cake created = cakeService.create(cake);
        return cakeMapper.toDto(created);
    }

    @PutMapping("{id}")
    public CakeDto update(@PathVariable Long id, @RequestBody CakeDto cakeDto) {
        Cake cake = cakeMapper.toEntity(cakeDto);
        Cake updated = cakeService.update(id, cake);
        return cakeMapper.toDto(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        cakeService.delete(id);
    }
}
