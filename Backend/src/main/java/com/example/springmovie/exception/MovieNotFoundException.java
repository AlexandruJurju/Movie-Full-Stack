package com.example.springmovie.exception;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
