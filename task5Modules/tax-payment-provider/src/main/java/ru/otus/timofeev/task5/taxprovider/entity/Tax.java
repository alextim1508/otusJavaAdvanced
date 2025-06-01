package ru.otus.timofeev.task5.taxprovider.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.otus.timofeev.task5.core.entity.Entity;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class Tax extends Entity<Long> {
    private final LocalDateTime dateTime;
}
