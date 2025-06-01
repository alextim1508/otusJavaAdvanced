package ru.otus.timofeev.task5.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaxDto {
    private Long id;
    private LocalDateTime dateTime;
}
