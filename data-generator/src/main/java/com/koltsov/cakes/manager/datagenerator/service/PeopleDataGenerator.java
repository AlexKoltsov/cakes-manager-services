package com.koltsov.cakes.manager.datagenerator.service;

import com.koltsov.cakes.manager.client.UserClient;
import com.koltsov.cakes.manager.datagenerator.data.CakeManagerService;
import com.koltsov.cakes.manager.datagenerator.data.CountStatistic;
import com.koltsov.cakes.manager.datagenerator.props.DataGeneratorPeopleProperties;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataRequest;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataResponse;
import com.koltsov.cakes.manager.web.dto.user.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import static com.koltsov.cakes.manager.datagenerator.utils.CommonUtils.randomElemInList;
import static com.koltsov.cakes.manager.datagenerator.utils.CommonUtils.randomLocalDate;

@RequiredArgsConstructor
@Component
public class PeopleDataGenerator implements DataGenerator {

    private final UserClient userClient;
    private final DataGeneratorPeopleProperties properties;
    private final Counter counter;

    @Override
    public GenerateDataResponse generate(GenerateDataRequest generateDataRequest) {
        CountStatistic countStatistic = counter.count(() -> {
            UserCreateDto userCreateDto = generateUser();
            userClient.createUser(userCreateDto);
        }, generateDataRequest.getSize());
        return GenerateDataResponse.builder(countStatistic.getSuccess())
                .failed(countStatistic.getFailed())
                .build();
    }

    @Override
    public CakeManagerService service() {
        return CakeManagerService.PEOPLE_SERVICE;
    }

    private UserCreateDto generateUser() {
        Gender gender = randomGender();
        String randomFirstName = randomFirstName(gender);
        String randomLastName = randomLastName(gender);
        String login = "%s_%s".formatted(randomFirstName, randomLastName).toLowerCase(Locale.ROOT);
        String email = "%s@%s".formatted(login, randomMailProvider());
        return new UserCreateDto(login, randomFirstName, randomLastName, randomDateBirth(), email);
    }

    private Gender randomGender() {
        return ThreadLocalRandom.current().nextBoolean() ? Gender.MALE : Gender.FEMALE;
    }

    private String randomFirstName(Gender gender) {
        List<String> names = switch (gender) {
            case MALE -> properties.getFirstNames().getMale();
            case FEMALE -> properties.getFirstNames().getFemale();
        };
        return randomElemInList(names);
    }

    private String randomLastName(Gender gender) {
        String lastName = randomElemInList(properties.getLastNames());
        if (gender == Gender.FEMALE) {
            lastName += "а";
        }
        return lastName;
    }

    private LocalDate randomDateBirth() {
        return randomLocalDate(properties.getDateBirthRange().getMin(), properties.getDateBirthRange().getMax());
    }

    private String randomMailProvider() {
        return randomElemInList(properties.getMailProviders());
    }

    private enum Gender {
        MALE, FEMALE
    }
}
