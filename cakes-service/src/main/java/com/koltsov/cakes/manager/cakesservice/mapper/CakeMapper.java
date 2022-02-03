package com.koltsov.cakes.manager.cakesservice.mapper;

import com.koltsov.cakes.manager.cakesservice.data.Cake;
import com.koltsov.cakes.manager.web.dto.cake.CakeCreateDto;
import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import com.koltsov.cakes.manager.mapper.CakeMangerMapperConfig;
import com.koltsov.cakes.manager.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CakeMangerMapperConfig.class)
public interface CakeMapper extends GenericMapper<Cake, CakeDto, CakeCreateDto> {
    @Mapping(target = "id", ignore = true)
    @Override
    Cake toNewEntity(CakeCreateDto createDto);
}
