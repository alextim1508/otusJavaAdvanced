package ru.otus.timofeev.task4.exception;

public class MethodArgumentMismatchException extends RuntimeException {
    public MethodArgumentMismatchException(String message) {
        super(message);
    }
}