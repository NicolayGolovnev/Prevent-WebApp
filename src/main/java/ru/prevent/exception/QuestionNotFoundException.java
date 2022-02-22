package ru.prevent.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
