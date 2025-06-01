package ru.otus.timofeev.task5.hotelservice.repository;

import org.springframework.stereotype.Repository;
import ru.otus.timofeev.task5.core.repository.InMemoryRepository;
import ru.otus.timofeev.task5.core.util.IdGenerator;
import ru.otus.timofeev.task5.hotelservice.entity.Reservation;

@Repository
public class ReservationRepository extends InMemoryRepository<Long, Reservation> {
    public ReservationRepository(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}
