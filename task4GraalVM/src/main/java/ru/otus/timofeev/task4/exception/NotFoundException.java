package ru.otus.timofeev.task4.exception;

import ru.otus.timofeev.task4.exception.AbstractWithRejectedFieldException;

public class NotFoundException extends AbstractWithRejectedFieldException {

    public NotFoundException(String message, String rejectedValue) {
        super(message, rejectedValue);
    }
}