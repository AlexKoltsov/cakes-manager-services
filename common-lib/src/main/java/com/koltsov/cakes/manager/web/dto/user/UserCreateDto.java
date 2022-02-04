package com.koltsov.cakes.manager.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDto {
    private String login;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}
