package com.koltsov.cakes.manager.datagenerator.service;

import com.koltsov.cakes.manager.client.CakeClient;
import com.koltsov.cakes.manager.client.OrderClient;
import com.koltsov.cakes.manager.client.UserClient;
import com.koltsov.cakes.manager.datagenerator.data.CakeManagerService;
import com.koltsov.cakes.manager.datagenerator.data.CountStatistic;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataRequest;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataResponse;
import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import com.koltsov.cakes.manager.web.dto.order.OrderCreateDto;
import com.koltsov.cakes.manager.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.koltsov.cakes.manager.datagenerator.utils.CommonUtils.randomElemInList;
import static com.koltsov.cakes.manager.datagenerator.utils.CommonUtils.randomLocalDateTime;
import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Component
public class OrdersDataGenerator implements DataGenerator {

    private final OrderClient orderClient;
    private final CakeClient cakeClient;
    private final UserClient userClient;
    private final Counter counter;

    @Override
    public GenerateDataResponse generate(GenerateDataRequest generateDataRequest) {
        List<Long> cakeIds = cakeClient.getAllCakes(Pageable.unpaged()).stream().map(CakeDto::getId).toList();
        List<Long> userIds = userClient.getAllUsers(Pageable.unpaged()).stream().map(UserDto::getId).toList();

        List<String> metadata = new ArrayList<>();
        CountStatistic countStatistic = counter.count(() -> {
            try {
                OrderCreateDto orderCreateDto = generateOrder(cakeIds, userIds);
                orderClient.createOrder(orderCreateDto);
            } catch (Exception e) {
                metadata.add(e.getMessage());
                throw e;
            }
        }, generateDataRequest.getSize());

        GenerateDataResponse.GenerateDataResponseBuilder responseBuilder = GenerateDataResponse.builder(countStatistic.getSuccess())
                .failed(countStatistic.getFailed());
        if (!metadata.isEmpty()) {
            responseBuilder.description(metadata.stream().distinct().collect(Collectors.joining("\n")));
        }
        return responseBuilder.build();
    }

    @Override
    public CakeManagerService service() {
        return CakeManagerService.ORDERS_SERVICE;
    }

    private OrderCreateDto generateOrder(List<Long> cakeIds, List<Long> userIds) {
        if (cakeIds.isEmpty() || userIds.isEmpty()) {
            throw new IllegalStateException("To create order we need to have at least 1 cake and 1 user");
        }
        return new OrderCreateDto(
                randomElemInList(cakeIds),
                randomElemInList(userIds),
                randomDeliveryDate()
        );
    }

    private LocalDateTime randomDeliveryDate() {
        return randomLocalDateTime(now(), now().plusMonths(1));
    }
}
