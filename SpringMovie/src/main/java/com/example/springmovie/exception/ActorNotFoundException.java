package com.example.springmovie.exception;

public class ActorNotFoundException extends Exception {
    public ActorNotFoundException(String message) {
        super(message);
    }
}
