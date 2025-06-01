package ru.otus.timofeev.task5.api.service;

import org.mapstruct.Mapper;
import ru.otus.timofeev.task5.api.dto.NewPersonDto;
import ru.otus.timofeev.task5.api.dto.PersonDto;
import ru.otus.timofeev.task5.hotelservice.entity.Person;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PersonMapper {
    Person toEntity(NewPersonDto personDto);

    PersonDto toDto(Person person);
}
