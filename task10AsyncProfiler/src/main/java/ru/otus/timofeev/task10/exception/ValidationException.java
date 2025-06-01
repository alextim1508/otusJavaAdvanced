package ru.otus.timofeev.task10.exception;

public class ValidationException extends AbstractWithRejectedFieldException {

    public ValidationException(String message, String rejectedValue) {
        super(message, rejectedValue);
    }
}