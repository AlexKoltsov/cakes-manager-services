package com.koltsov.cakes.manager.datagenerator.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(builderMethodName = "hiddenBuilder")
@Data
public class GenerateDataResponse {

    private Integer added;
    private Integer failed;
    private String description;

    public static GenerateDataResponseBuilder builder(Integer added) {
        return hiddenBuilder().added(added);
    }
}
