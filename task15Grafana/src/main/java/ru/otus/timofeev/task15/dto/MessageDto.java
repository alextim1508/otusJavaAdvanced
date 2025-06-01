package ru.otus.timofeev.task15.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MessageDto {

    @NonNull
    public String message;
}
