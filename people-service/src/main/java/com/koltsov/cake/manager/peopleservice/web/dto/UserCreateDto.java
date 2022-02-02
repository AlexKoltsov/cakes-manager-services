package com.koltsov.cake.manager.peopleservice.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateDto {
    private String login;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}
