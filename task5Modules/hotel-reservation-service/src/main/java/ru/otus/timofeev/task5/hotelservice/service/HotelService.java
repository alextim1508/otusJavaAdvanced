package ru.otus.timofeev.task5.hotelservice.service;

import ru.otus.timofeev.task5.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    Hotel findById(long id);

    List<Hotel> findAll();
}
