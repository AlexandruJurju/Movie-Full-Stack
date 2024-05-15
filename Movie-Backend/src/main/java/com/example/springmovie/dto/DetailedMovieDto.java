package com.example.springmovie.dto;

import com.example.springmovie.enums.ReleaseStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.example.springmovie.model.Movie}
 */
public record DetailedMovieDto(
        @NotNull Long id,
        @NotNull @NotEmpty String title,
        @NotNull @NotEmpty String headline,
        @NotNull @NotEmpty String overview,
        Integer runtimeInMinutes,
        @NotNull @NotEmpty String posterUrl,
        @NotNull LocalDate releaseDate,
        @NotNull ReleaseStatus releaseStatus,
        @NotNull Set<GenreDto> genres,
        @NotNull Set<MovieActorDto> movieActors) implements Serializable {
}