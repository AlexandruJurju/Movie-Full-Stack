package com.example.springmovie.exception;

public class MovieInvalidIdException extends RuntimeException {

    public MovieInvalidIdException(String message) {
        super(message);
    }
}
