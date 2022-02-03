package com.koltsov.cake.manager.peopleservice.mapper;

import com.koltsov.cake.manager.peopleservice.data.User;
import com.koltsov.cakes.manager.web.dto.user.UserCreateDto;
import com.koltsov.cakes.manager.web.dto.user.UserDto;
import com.koltsov.cakes.manager.mapper.CakeMangerMapperConfig;
import com.koltsov.cakes.manager.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CakeMangerMapperConfig.class)
public interface UserMapper extends GenericMapper<User, UserDto, UserCreateDto> {
    @Mapping(target = "id", ignore = true)
    @Override
    User toNewEntity(UserCreateDto createDto);
}
