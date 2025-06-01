package ru.otus.timofeev.task5.hotelservice.exception;

import lombok.Getter;

public class ReservationNotFoundException extends RuntimeException {

    @Getter
    private final long reservationId;

    public ReservationNotFoundException(long reservationId) {
        super("Reservation with ID %d not found".formatted(reservationId));
        this.reservationId = reservationId;
    }
}