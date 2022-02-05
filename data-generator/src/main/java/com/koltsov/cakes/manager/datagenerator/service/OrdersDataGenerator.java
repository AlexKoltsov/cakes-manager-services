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
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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
        CountStatistic countStatistic = counter.count(() -> {
            OrderCreateDto orderCreateDto = generateOrder();
            orderClient.createOrder(orderCreateDto);
        }, generateDataRequest.getSize());

        return GenerateDataResponse.builder(countStatistic.getSuccess())
                .failed(countStatistic.getFailed())
                .build();
    }

    @Override
    public CakeManagerService service() {
        return CakeManagerService.ORDERS_SERVICE;
    }

    private OrderCreateDto generateOrder() {
        List<Long> cakeIds = cakeClient.getAllCakes().stream().map(CakeDto::getId).toList();
        List<Long> userIds = userClient.getAllUsers().stream().map(UserDto::getId).toList();
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
