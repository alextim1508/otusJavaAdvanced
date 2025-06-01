package ru.otus.timofeev.task9.exception;

public class ConflictException extends AbstractWithRejectedFieldException {

    public ConflictException(String message, String rejectedValue) {
        super(message, rejectedValue);
    }
}
