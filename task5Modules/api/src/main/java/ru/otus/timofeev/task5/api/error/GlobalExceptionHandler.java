package ru.otus.timofeev.task5.api.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import ru.otus.timofeev.task5.hotelservice.exception.HotelNotFoundException;
import ru.otus.timofeev.task5.hotelservice.exception.PersonNotFoundException;
import ru.otus.timofeev.task5.hotelservice.exception.ReservationNotFoundException;
import ru.otus.timofeev.task5.taxprovider.exception.TaxNotFoundException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({
            TaxNotFoundException.class,
            HotelNotFoundException.class,
            PersonNotFoundException.class,
            ReservationNotFoundException.class
    })
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handle(RuntimeException ex) {
        return new ErrorResponse(NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(IllegalArgumentException ex) {
        return new ErrorResponse(BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(MethodArgumentNotValidException ex) {
        return new ErrorResponse(BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(HandlerMethodValidationException ex) {
        return new ErrorResponse(BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handle(Exception ex) {
        log.error("", ex);
        return new ErrorResponse( INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now());
    }
}
