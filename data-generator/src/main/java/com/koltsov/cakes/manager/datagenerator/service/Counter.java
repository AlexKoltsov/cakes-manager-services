package com.koltsov.cakes.manager.datagenerator.service;

import com.koltsov.cakes.manager.datagenerator.data.CountStatistic;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

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
            }
        }
        return countStatistic;
    }
}
