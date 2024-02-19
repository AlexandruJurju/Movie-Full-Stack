package com.example.springmovie.dto.request;

import jakarta.validation.constraints.*;

public record RegisterRequest(
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

        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 8)
        // pattern for min 8 characters, at least one letter, one number and one special character
        //        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
        String password) {
}