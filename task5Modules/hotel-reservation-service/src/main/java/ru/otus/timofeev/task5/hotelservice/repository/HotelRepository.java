package ru.otus.timofeev.task5.hotelservice.repository;

import org.springframework.stereotype.Repository;
import ru.otus.timofeev.task5.core.repository.InMemoryRepository;
import ru.otus.timofeev.task5.core.util.IdGenerator;
import ru.otus.timofeev.task5.hotelservice.entity.Hotel;

@Repository
public class HotelRepository  extends InMemoryRepository<Long, Hotel> {
    public HotelRepository(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}