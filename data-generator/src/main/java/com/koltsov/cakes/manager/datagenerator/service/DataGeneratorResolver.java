package com.koltsov.cakes.manager.datagenerator.service;

import com.koltsov.cakes.manager.datagenerator.data.CakeManagerService;
import com.koltsov.cakes.manager.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataGeneratorResolver {

    private final Map<CakeManagerService, DataGenerator> dataGeneratorsMap;

    @Autowired
    public DataGeneratorResolver(List<DataGenerator> dataGenerators) {
        dataGeneratorsMap = dataGenerators.stream()
                .collect(Collectors.toMap(DataGenerator::service, Function.identity()));
    }

    public DataGenerator resolve(CakeManagerService cakeManagerService) {
        return Optional.ofNullable(dataGeneratorsMap.get(cakeManagerService))
                .orElseThrow(() -> new NotFoundException(
                        "Service %s not supported yet for data generation".formatted(cakeManagerService.toString())
                ));
    }
}
