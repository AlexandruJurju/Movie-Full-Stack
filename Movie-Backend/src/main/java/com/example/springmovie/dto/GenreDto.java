package com.example.springmovie.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springmovie.model.Genre}
 */
public record GenreDto(
        Long id,
        @NotNull @NotEmpty String name) implements Serializable {
}