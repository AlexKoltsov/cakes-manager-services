package com.koltsov.cakes.manager.cakesservice.mapper;

import com.koltsov.cakes.manager.cakesservice.data.Cake;
import com.koltsov.cakes.manager.cakesservice.web.dto.CakeCreateDto;
import com.koltsov.cakes.manager.cakesservice.web.dto.CakeDto;
import com.koltsov.cakes.manager.mapper.CakeMangerMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CakeMangerMapperConfig.class)
public interface CakeMapper {

    CakeDto toDto(Cake cake);

    Cake toEntity(CakeDto cakeDto);

    @Mapping(target = "id", ignore = true)
    Cake toNewEntity(CakeCreateDto cakeCreateDto);
}
