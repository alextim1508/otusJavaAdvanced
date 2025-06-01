package ru.otus.timofeev.task9.exception;

public class MethodArgumentMismatchException extends RuntimeException {
    public MethodArgumentMismatchException(String message) {
        super(message);
    }
}