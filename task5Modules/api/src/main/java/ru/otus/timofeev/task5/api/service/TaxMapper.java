package ru.otus.timofeev.task5.api.service;

import org.mapstruct.Mapper;
import ru.otus.timofeev.task5.api.dto.TaxDto;
import ru.otus.timofeev.task5.taxprovider.entity.Tax;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TaxMapper {
    TaxDto toDto(Tax tax);
}
