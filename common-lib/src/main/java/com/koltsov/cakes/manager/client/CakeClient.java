package com.koltsov.cakes.manager.client;

import com.koltsov.cakes.manager.web.dto.cake.CakeCreateDto;
import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "CAKES-SERVICE", path = "/api/v1/cakes")
public interface CakeClient {

    @GetMapping
    Page<CakeDto> getAllCakes(@RequestBody Pageable pageable);

    @GetMapping("{cakeId}")
    CakeDto getCakeById(@PathVariable("cakeId") Long cakeId);

    @PostMapping
    CakeDto createCake(@RequestBody CakeCreateDto cakeCreateDto);

    @PutMapping("{cakeId}")
    CakeDto updateCakeById(@PathVariable("cakeId") Long cakeId, @RequestBody CakeDto cakeDto);

    @DeleteMapping("{cakeId}")
    void deleteCakeById(@PathVariable("cakeId") Long cakeId);
}
