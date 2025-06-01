package ru.otus.timofeev.task15.dto;


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
