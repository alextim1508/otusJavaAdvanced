package ru.otus.timofeev.task5.hotelservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.timofeev.task5.core.entity.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
public class Hotel extends Entity<Long> {
    private final String address;
}
