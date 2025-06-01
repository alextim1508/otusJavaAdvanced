package ru.otus.timofeev.task5.api.service;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.timofeev.task5.api.dto.NewReservationDto;
import ru.otus.timofeev.task5.api.dto.ReservationDto;
import ru.otus.timofeev.task5.hotelservice.entity.Reservation;
import ru.otus.timofeev.task5.hotelservice.service.HotelService;
import ru.otus.timofeev.task5.hotelservice.service.PersonService;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = {PersonService.class, HotelService.class, PersonMapper.class, HotelMapper.class, TaxMapper.class}
)
@Slf4j
public abstract class ReservationMapper {

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "hotelId", target = "hotel")
    public abstract Reservation toEntity(NewReservationDto newReservationDto);

    public abstract ReservationDto toDto(Reservation reservation);
}
