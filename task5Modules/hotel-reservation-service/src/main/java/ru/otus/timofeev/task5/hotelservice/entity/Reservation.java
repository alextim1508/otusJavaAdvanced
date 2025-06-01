package ru.otus.timofeev.task5.hotelservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.timofeev.task5.core.entity.Entity;
import ru.otus.timofeev.task5.taxprovider.entity.Tax;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class Reservation extends Entity<Long> {
    private final Person person;
    private final Hotel hotel;
    private final LocalDateTime entry;
    private final LocalDateTime exit;
    private Tax tax;
}
