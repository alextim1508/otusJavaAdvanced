package ru.otus.timofeev.task14.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorDto {

    public String message;

    public String reason;

    public HttpStatus status;

    public List<String> errors;

    public LocalDateTime timestamp;
}