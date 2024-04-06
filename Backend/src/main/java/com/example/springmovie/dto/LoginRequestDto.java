package com.example.springmovie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record LoginRequestDto(
        @NotNull @NotEmpty @NotBlank String username,
        @NotNull @NotEmpty @NotBlank String password
) {
}