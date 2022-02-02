package com.koltsov.cake.manager.peopleservice.mapper;

import com.koltsov.cake.manager.peopleservice.data.User;
import com.koltsov.cake.manager.peopleservice.web.dto.UserCreateDto;
import com.koltsov.cake.manager.peopleservice.web.dto.UserDto;
import com.koltsov.cakes.manager.mapper.CakeMangerMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CakeMangerMapperConfig.class)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    User toNewEntity(UserCreateDto userCreateDto);
}
