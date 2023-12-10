package com.example.backend.exception;

public class MovieInvalidIdException extends RuntimeException {

    public MovieInvalidIdException(String message) {
        super(message);
    }
}
