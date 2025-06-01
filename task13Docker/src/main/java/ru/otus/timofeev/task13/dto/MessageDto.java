package ru.otus.timofeev.task13.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MessageDto {

    @NonNull
    public String message;
}
