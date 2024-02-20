package com.example.springmovie.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class ReviewDto {

    @NotNull
    @NotBlank
    private final Long movieId;

    @NotNull
    @NotBlank
    private final Long userId;

    @NotNull
    @NotBlank
    private final String text;

    @NotNull
    @Min(0)
    @Max(5)
    private final Integer score;

    @NotNull
    private final LocalDate postedDate;
}