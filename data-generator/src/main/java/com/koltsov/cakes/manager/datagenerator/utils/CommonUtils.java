package com.koltsov.cakes.manager.datagenerator.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class CommonUtils {

    public static <T> T randomElemInList(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static LocalDate randomLocalDate(LocalDate min, LocalDate max) {
        long minDay = min.toEpochDay();
        long maxDay = max.toEpochDay();
        long random = ThreadLocalRandom
                .current()
                .nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(random);
    }

    public static LocalDateTime randomLocalDateTime(LocalDateTime min, LocalDateTime max) {
        return randomLocalDateTime(min, max, ChronoUnit.HOURS);
    }

    public static LocalDateTime randomLocalDateTime(LocalDateTime min, LocalDateTime max, TemporalUnit truncatedTo) {
        long minDay = min.truncatedTo(ChronoUnit.HOURS).toEpochSecond(ZoneOffset.UTC);
        long maxDay = max.truncatedTo(ChronoUnit.HOURS).toEpochSecond(ZoneOffset.UTC);
        long random = ThreadLocalRandom
                .current()
                .nextLong(minDay, maxDay);

        return LocalDateTime.ofEpochSecond(random, 0, ZoneOffset.UTC);
    }
}
