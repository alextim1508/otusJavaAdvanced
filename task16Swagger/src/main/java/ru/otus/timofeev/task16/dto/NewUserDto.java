package ru.otus.timofeev.task16.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
@Schema(description = "New user")
public class NewUserDto {

    @NotNull(message = "{user.name.not_null}")
    @Size(max = 255, message = "{user.name.length}")
    @NotBlank(message = "{user.name.not_blank}")
    @Schema(description = "User name", example = "alex")
    private String name;

    @NotNull(message = "{user.email.not_null}")
    @Email(message = "{user.email.not_valid}")
    @Size(max = 255, message = "{user.email.length}")
    @NotBlank(message = "{user.email.not_blank}")
    @Schema(description = "User email", example = "alex@mail.com")
    private String email;

    @NotNull(message = "{user.password.not_null}")
    @Size(max = 255, message = "{user.pass.length}")
    @NotBlank(message = "{user.pass.not_blank}")
    @Schema(description = "User password", maxLength = 255, example = "qwert123")
    private String password;
}

