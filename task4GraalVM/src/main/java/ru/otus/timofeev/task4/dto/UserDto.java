package ru.otus.timofeev.task4.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NonNull
    public String name;

    @NonNull
    public String email;
}
