package ru.otus.timofeev.task16.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User")
public class UserDto {

    @NonNull
    @Schema(description = "User name", example = "alex")
    public String name;

    @NonNull
    @Schema(description = "User email", example = "alex@mail.com")
    public String email;
}
