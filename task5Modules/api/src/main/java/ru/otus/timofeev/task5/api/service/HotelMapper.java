package ru.otus.timofeev.task5.api.service;

import org.mapstruct.Mapper;
import ru.otus.timofeev.task5.api.dto.HotelDto;
import ru.otus.timofeev.task5.api.dto.NewHotelDto;
import ru.otus.timofeev.task5.hotelservice.entity.Hotel;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface HotelMapper {
    Hotel toEntity(NewHotelDto hotelDto);

    HotelDto toDto(Hotel hotel);
}
