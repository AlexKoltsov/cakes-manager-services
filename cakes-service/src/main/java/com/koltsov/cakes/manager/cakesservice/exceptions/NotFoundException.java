package com.koltsov.cakes.manager.cakesservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Resource with id [%s] not found".formatted(id));
    }
}
