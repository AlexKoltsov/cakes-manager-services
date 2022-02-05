package com.koltsov.cakes.manager.datagenerator.service;

import com.koltsov.cakes.manager.datagenerator.data.CountStatistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class Counter {
    public CountStatistic count(Runnable runnable, int times) {
        CountStatistic countStatistic = new CountStatistic();
        for (int i = 0; i < times; i++) {
            try {
                runnable.run();
                countStatistic.success();
            } catch (Exception e) {
                countStatistic.fail();
                log.warn(e.getMessage());
            }
        }
        return countStatistic;
    }

    public CountStatistic count(Consumer<Integer> indexConsumer, int times) {
        CountStatistic countStatistic = new CountStatistic();
        for (int i = 0; i < times; i++) {
            try {
                indexConsumer.accept(i);
                countStatistic.success();
            } catch (Exception e) {
                countStatistic.fail();
                log.warn(e.getMessage());
            }
        }
        return countStatistic;
    }
}
