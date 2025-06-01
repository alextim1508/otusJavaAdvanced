package ru.otus.timofeev.task5.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewReservationDto {
    @NotNull
    public Long personId;
    @NotNull
    public Long hotelId;
    @NotNull
    public LocalDateTime entry;
    @NotNull
    public LocalDateTime exit;
}
