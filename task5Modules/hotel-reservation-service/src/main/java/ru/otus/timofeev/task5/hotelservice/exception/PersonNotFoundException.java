package ru.otus.timofeev.task5.hotelservice.exception;

import lombok.Getter;

public class PersonNotFoundException extends RuntimeException {

    @Getter
    private final long personId;

    public PersonNotFoundException(long personId) {
        super("Person with ID %d not found".formatted(personId));
        this.personId = personId;
    }
}