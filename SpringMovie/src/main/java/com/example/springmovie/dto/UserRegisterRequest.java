package com.example.springmovie.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record UserRegisterRequest(
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 6, max = 32)
        String username,
        @NotNull
        @NotEmpty
        @NotBlank
        @Email
        String email,
        // pattern for min 8 characters, at least one letter, one number and one special character
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 8)
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
        String password)
        implements Serializable {
}