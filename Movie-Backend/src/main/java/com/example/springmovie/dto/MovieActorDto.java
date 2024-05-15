package com.example.springmovie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springmovie.model.MovieActor}
 */
public record MovieActorDto(
        @NotNull Long id,
        @NotNull ActorDto actor,
        @NotNull @NotEmpty String role,
        @NotNull @PositiveOrZero Integer displayOrder,
        @NotNull @NotEmpty String characterImageUrl) implements Serializable {
}