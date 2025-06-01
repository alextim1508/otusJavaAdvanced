package ru.otus.timofeev.task5.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.timofeev.task5.api.dto.NewReservationDto;
import ru.otus.timofeev.task5.api.dto.ReservationDto;
import ru.otus.timofeev.task5.api.service.ReservationMapper;
import ru.otus.timofeev.task5.hotelservice.entity.Reservation;
import ru.otus.timofeev.task5.hotelservice.service.ReservationService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService service;

    private final ReservationMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public ReservationDto createReservation(@Valid @RequestBody NewReservationDto newReservationDto) {
        log.info("Creation reservation request: {}", newReservationDto);
        Reservation reservation = mapper.toEntity(newReservationDto);

        log.info("reservation : {}", reservation);
        Reservation savedReservation = service.create(reservation);

        ReservationDto dto = mapper.toDto(savedReservation);
        log.info("response: {}", dto);
        return dto;
    }
}
