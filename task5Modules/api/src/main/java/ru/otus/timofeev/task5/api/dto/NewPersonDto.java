package ru.otus.timofeev.task5.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewPersonDto {
    @NotBlank
    private String login;
}
