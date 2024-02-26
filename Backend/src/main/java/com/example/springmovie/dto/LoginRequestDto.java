package com.example.springmovie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

    @NotNull
    @NotEmpty
    @NotBlank
    private final String username;

    @NotNull
    @NotEmpty
    @NotBlank
    private final String password;
}