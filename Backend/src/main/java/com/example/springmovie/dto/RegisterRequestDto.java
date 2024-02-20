package com.example.springmovie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @Size(min = 6, max = 32)
        String username,
        @Email
        String email,
        @Size(min = 8)
        // pattern for min 8 characters, at least one letter, one number and one special character
        //        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
        String password) {
}