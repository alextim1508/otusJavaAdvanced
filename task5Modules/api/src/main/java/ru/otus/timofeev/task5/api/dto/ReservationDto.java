package ru.otus.timofeev.task5.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {
    private HotelDto hotel;
    private PersonDto person;
    private LocalDateTime entry;
    private LocalDateTime exit;
    private TaxDto tax;
}
