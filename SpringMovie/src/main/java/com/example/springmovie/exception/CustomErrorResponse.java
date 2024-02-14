package com.example.springmovie.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponse {

    private String message;
    private long timestamp;

    public CustomErrorResponse(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}