package com.example.springmovie.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.springmovie.model.Actor}
 */
public record ActorDto(
        @NotNull Long id,
        @NotNull @NotEmpty String firstName,
        @NotNull @NotEmpty String lastName,
        LocalDate birthDate,
        @NotNull @NotEmpty String profilePicUrl,
        String biography) implements Serializable {
}