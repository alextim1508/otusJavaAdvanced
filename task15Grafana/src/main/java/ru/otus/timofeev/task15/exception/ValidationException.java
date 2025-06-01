package ru.otus.timofeev.task15.exception;

public class ValidationException extends AbstractWithRejectedFieldException {

    public ValidationException(String message, String rejectedValue) {
        super(message, rejectedValue);
    }
}