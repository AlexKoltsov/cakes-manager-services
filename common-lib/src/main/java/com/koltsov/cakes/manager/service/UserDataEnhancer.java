package com.koltsov.cakes.manager.service;

import com.koltsov.cakes.manager.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class UserDataEnhancer implements DataEnhancer<UserDto, Long> {

    private final RestTemplate restTemplate;

    @Override
    public UserDto enhance(Long id) {
        ResponseEntity<UserDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8081/api/v1/users/%s".formatted(id), UserDto.class);
        return responseEntity.getBody();
    }
}
