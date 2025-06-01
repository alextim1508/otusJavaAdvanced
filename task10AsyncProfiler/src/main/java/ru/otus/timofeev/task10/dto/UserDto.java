package ru.otus.timofeev.task10.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NonNull
    public String name;

    @NonNull
    public String email;
}
