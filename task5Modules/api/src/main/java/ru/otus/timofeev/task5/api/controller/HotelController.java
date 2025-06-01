package ru.otus.timofeev.task5.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.timofeev.task5.api.dto.HotelDto;
import ru.otus.timofeev.task5.api.dto.NewHotelDto;
import ru.otus.timofeev.task5.api.service.HotelMapper;
import ru.otus.timofeev.task5.hotelservice.entity.Hotel;
import ru.otus.timofeev.task5.hotelservice.service.HotelService;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService service;

    private final HotelMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public HotelDto createHotel(@Valid @RequestBody NewHotelDto hotelDto) {
        log.info("Creation hotel request: {}", hotelDto);
        Hotel hotel = mapper.toEntity(hotelDto);

        log.info("hotel : {}", hotel);
        Hotel savedHotel = service.create(hotel);

        HotelDto dto = mapper.toDto(savedHotel);
        log.info("response: {}", dto);
        return dto;
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<HotelDto> getAllHotels() {
        log.info("Getting all hotels request");

        List<Hotel> hotels = service.findAll();
        log.info("hotels: {}", hotels);

        List<HotelDto> dtos = hotels.stream().map(mapper::toDto).collect(Collectors.toList());
        log.info("response: {}", dtos);
        return dtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public HotelDto getHotel(@PathVariable Long id) {
        log.info("Getting by {} id hotel request", id);

        Hotel byId = service.findById(id);
        log.info("hotel: {}", byId);

        HotelDto dto = mapper.toDto(byId);
        log.info("response: {}", dto);
        return dto;
    }
}
