package ru.otus.timofeev.task14.exception;

public class NotFoundException extends AbstractWithRejectedFieldException {

    public NotFoundException(String message, String rejectedValue) {
        super(message, rejectedValue);
    }
}