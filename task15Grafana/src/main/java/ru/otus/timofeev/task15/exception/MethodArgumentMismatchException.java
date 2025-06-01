package ru.otus.timofeev.task15.exception;


public class MethodArgumentMismatchException extends RuntimeException {
    public MethodArgumentMismatchException(String message) {
        super(message);
    }
}