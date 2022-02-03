package com.koltsov.cakes.manager.service;

import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class CakeDataEnhancer implements DataEnhancer<CakeDto, Long> {

    private final RestTemplate restTemplate;

    @Override
    public CakeDto enhance(Long id) {
        ResponseEntity<CakeDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/v1/cakes/%s".formatted(id), CakeDto.class);
        return responseEntity.getBody();
    }
}
