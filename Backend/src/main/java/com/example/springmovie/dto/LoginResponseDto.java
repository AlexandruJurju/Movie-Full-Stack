package com.example.springmovie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class LoginResponseDto {
    @NotNull
    @NotEmpty
    @NotBlank
    private final String token;
}
