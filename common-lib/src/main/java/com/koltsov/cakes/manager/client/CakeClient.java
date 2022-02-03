package com.koltsov.cakes.manager.client;

import com.koltsov.cakes.manager.web.dto.cake.CakeCreateDto;
import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "CAKES-SERVICE", path = "/api/v1/cakes")
public interface CakeClient {

    @GetMapping
    List<CakeDto> getAllCakes();

    @GetMapping("{cakeId}")
    CakeDto getCakeById(@PathVariable("cakeId") Long cakeId);

    @PostMapping
    CakeDto createCake(@RequestBody CakeCreateDto cakeCreateDto);

    @PutMapping("{cakeId}")
    CakeDto updateCakeById(@PathVariable("cakeId") Long cakeId, @RequestBody CakeDto cakeDto);

    @DeleteMapping("{cakeId}")
    void deleteCakeById(@PathVariable("cakeId") Long cakeId);
}
