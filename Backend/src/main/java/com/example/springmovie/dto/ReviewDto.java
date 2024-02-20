package com.example.springmovie.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * DTO for {@link com.example.springmovie.model.Review}
 */
public record ReviewDto(
        @NotNull @NotEmpty @NotBlank Long movieId,
        @NotNull @NotEmpty @NotBlank Long userId,
        @NotNull @NotEmpty @NotBlank String text,
        @NotNull @Min(0) @Max(5) Integer score,
        @NotNull LocalDate postedDate) {
}