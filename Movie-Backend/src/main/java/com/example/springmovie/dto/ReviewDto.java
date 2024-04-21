package com.example.springmovie.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record ReviewDto(
        @NotNull @NotBlank Long movieId,
        @NotNull @NotBlank Long userId,
        @NotNull @NotBlank String text,
        @NotNull @Min(0) @Max(5) Integer score,
        @NotNull LocalDate postedDate) {
}