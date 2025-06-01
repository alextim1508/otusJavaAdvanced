package ru.otus.timofeev.task10.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MessageDto {

    @NonNull
    public String message;
}
