package com.koltsov.cakes.manager.datagenerator.service;

import com.koltsov.cakes.manager.client.CakeClient;
import com.koltsov.cakes.manager.datagenerator.data.CakeManagerService;
import com.koltsov.cakes.manager.datagenerator.data.CountStatistic;
import com.koltsov.cakes.manager.datagenerator.props.DataGeneratorCakesProperties;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataRequest;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataResponse;
import com.koltsov.cakes.manager.web.dto.cake.CakeCreateDto;
import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.function.Predicate.not;

@RequiredArgsConstructor
@Component
public class CakesDataGenerator implements DataGenerator {

    private final CakeClient cakeClient;
    private final DataGeneratorCakesProperties dataGeneratorCakesProperties;
    private final Counter counter;

    @Override
    public GenerateDataResponse generate(GenerateDataRequest generateDataRequest) {
        List<String> existentCakes = cakeClient.getAllCakes(Pageable.unpaged()).stream().map(CakeDto::getName).toList();
        List<String> possibleCakesToCreate = dataGeneratorCakesProperties.getNames().stream()
                .filter(not(existentCakes::contains))
                .toList();

        if (possibleCakesToCreate.size() == 0) {
            return GenerateDataResponse.builder(0)
                    .description("Nothing to create")
                    .build();
        }
        if (possibleCakesToCreate.size() < generateDataRequest.getSize()) {
            return GenerateDataResponse.builder(0)
                    .description("Only %s cakes could be created. You specified %s"
                            .formatted(possibleCakesToCreate.size(), generateDataRequest.getSize()))
                    .build();

        }

        CountStatistic countStatistic = counter.count(
                index -> cakeClient.createCake(new CakeCreateDto(possibleCakesToCreate.get(index))),
                generateDataRequest.getSize()
        );

        return GenerateDataResponse.builder(countStatistic.getSuccess())
                .failed(countStatistic.getFailed())
                .build();

    }

    @Override
    public CakeManagerService service() {
        return CakeManagerService.CAKES_SERVICE;
    }
}
