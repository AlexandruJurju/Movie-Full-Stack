package com.example.springmovie.exception;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
