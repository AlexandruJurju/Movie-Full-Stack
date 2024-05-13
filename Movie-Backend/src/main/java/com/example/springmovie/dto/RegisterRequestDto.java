package com.example.springmovie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank @Size(min = 6, max = 32) String username,
        @Email @NotBlank String email,
        @NotBlank @Size(min = 8) String password) {

}