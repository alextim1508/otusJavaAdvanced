package ru.otus.timofeev.task7.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.timofeev.task7.dto.NewUserDto;
import ru.otus.timofeev.task7.dto.UserDto;
import ru.otus.timofeev.task7.model.User;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = {HashPasswordService.class}
)
public abstract class UserMapper {

    public abstract List<UserDto> toDto(List<User> users);

    public abstract UserDto toDto(User users);

    @Mapping(source = "password", target = "hashPass", qualifiedByName = "hashPass")
    public abstract User toEntity(NewUserDto userDto);
}
