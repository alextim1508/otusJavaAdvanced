package ru.otus.timofeev.task5.hotelservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.timofeev.task5.hotelservice.entity.Reservation;
import ru.otus.timofeev.task5.hotelservice.exception.ReservationNotFoundException;
import ru.otus.timofeev.task5.hotelservice.repository.ReservationRepository;
import ru.otus.timofeev.task5.taxprovider.entity.Tax;
import ru.otus.timofeev.task5.taxprovider.service.TaxService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    private final TaxService taxService;

    @Override
    public Reservation create(Reservation hotel) {
        Tax tax = taxService.create(new Tax(LocalDateTime.now()));
        hotel.setTax(tax);
        return repository.save(hotel);
    }

    @Override
    public Reservation findById(long id) {
        return repository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Override
    public List<Reservation> findAll() {
        return repository.findAll();
    }
}
