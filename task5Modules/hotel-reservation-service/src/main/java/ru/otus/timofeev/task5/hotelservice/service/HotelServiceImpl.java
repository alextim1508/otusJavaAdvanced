package ru.otus.timofeev.task5.hotelservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.timofeev.task5.hotelservice.entity.Hotel;
import ru.otus.timofeev.task5.hotelservice.exception.HotelNotFoundException;
import ru.otus.timofeev.task5.hotelservice.repository.HotelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository repository;

    @Override
    public Hotel create(Hotel hotel) {
        return repository.save(hotel);
    }

    @Override
    public Hotel findById(long id) {
        return repository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Override
    public List<Hotel> findAll() {
        return repository.findAll();
    }
}
