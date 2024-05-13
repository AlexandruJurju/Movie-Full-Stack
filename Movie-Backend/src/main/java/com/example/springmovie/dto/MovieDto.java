package com.example.springmovie.dto;

import com.example.springmovie.enums.ReleaseStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.springmovie.model.Movie}
 */
public record MovieDto(
        @NotNull Long id,
        @NotNull @NotEmpty String title,
        @NotNull @NotEmpty String headline,
        @NotNull @NotEmpty String overview,
        Integer runtimeInMinutes,
        String posterUrl,
        @NotNull LocalDate releaseDate,
        ReleaseStatus releaseStatus) implements Serializable {
}