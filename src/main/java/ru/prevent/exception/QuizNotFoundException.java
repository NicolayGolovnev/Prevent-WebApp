package ru.prevent.exception;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException() {
        super();
    }

    public QuizNotFoundException(String message) {
        super(message);
    }

    public QuizNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
