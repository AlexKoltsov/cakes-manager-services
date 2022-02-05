package com.koltsov.cakes.manager.datagenerator.web.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class GenerateDataRequest {
    @Min(1)
    @Max(100)
    private Integer size = 10;
}
