package ru.otus.timofeev.task5.hotelservice.service;

import ru.otus.timofeev.task5.hotelservice.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation create(Reservation reservation);

    Reservation findById(long id);

    List<Reservation> findAll();
}
