package ru.otus.timofeev.task5.hotelservice.exception;

import lombok.Getter;

public class HotelNotFoundException extends RuntimeException {

    @Getter
    private final long hotelId;

    public HotelNotFoundException(long hotelId) {
        super("Hotel with ID %d not found".formatted(hotelId));
        this.hotelId = hotelId;
    }
}