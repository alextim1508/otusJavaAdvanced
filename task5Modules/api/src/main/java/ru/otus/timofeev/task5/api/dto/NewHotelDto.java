package ru.otus.timofeev.task5.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewHotelDto {
    @NotBlank
    private String address;
}
