package ru.otus.timofeev.task4.exception;

import ru.otus.timofeev.task4.exception.AbstractWithRejectedFieldException;

public class ConflictException extends AbstractWithRejectedFieldException {

    public ConflictException(String message, String rejectedValue) {
        super(message, rejectedValue);
    }
}
