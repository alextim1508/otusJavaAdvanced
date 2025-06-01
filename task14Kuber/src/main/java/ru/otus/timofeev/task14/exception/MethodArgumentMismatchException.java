package ru.otus.timofeev.task14.exception;


public class MethodArgumentMismatchException extends RuntimeException {
    public MethodArgumentMismatchException(String message) {
        super(message);
    }
}