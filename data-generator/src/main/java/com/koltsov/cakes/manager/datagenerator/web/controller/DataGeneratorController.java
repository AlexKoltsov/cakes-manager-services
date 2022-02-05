package com.koltsov.cakes.manager.datagenerator.web.controller;


import com.koltsov.cakes.manager.datagenerator.service.DataGeneratorResolver;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataRequest;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.koltsov.cakes.manager.datagenerator.data.CakeManagerService.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/data-generator")
public class DataGeneratorController {

    private final DataGeneratorResolver dataGeneratorResolver;

    @PostMapping("/cakes")
    public GenerateDataResponse generateCakesData(@Validated @RequestBody GenerateDataRequest generateDataRequest) {
        return dataGeneratorResolver.resolve(CAKES_SERVICE).generate(generateDataRequest);
    }

    @PostMapping("/users")
    public GenerateDataResponse generateUsersData(@Validated @RequestBody GenerateDataRequest generateDataRequest) {
        return dataGeneratorResolver.resolve(PEOPLE_SERVICE).generate(generateDataRequest);
    }

    @PostMapping("/orders")
    public GenerateDataResponse generateOrdersData(@Validated @RequestBody GenerateDataRequest generateDataRequest) {
        return dataGeneratorResolver.resolve(ORDERS_SERVICE).generate(generateDataRequest);
    }
}
