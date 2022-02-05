package com.koltsov.cakes.manager.datagenerator.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "data.generator.people")
public class DataGeneratorPeopleProperties {
    private FirstName firstNames;
    private List<String> lastNames;
    private DateBirthRange dateBirthRange;
    private List<String> mailProviders;

    @Data
    public static class FirstName {
        private List<String> male;
        private List<String> female;
    }

    @Data
    public static class DateBirthRange {
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate min;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate max;
    }
}
