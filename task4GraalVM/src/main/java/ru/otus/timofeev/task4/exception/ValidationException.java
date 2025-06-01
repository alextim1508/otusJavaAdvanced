package ru.otus.timofeev.task4.exception;

import ru.otus.timofeev.task4.exception.AbstractWithRejectedFieldException;

public class ValidationException extends AbstractWithRejectedFieldException {

    public ValidationException(String message, String rejectedValue) {
        super(message, rejectedValue);
    }
}