package com.example.springmovie.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springmovie.model.Actor}
 */
public record ActorDisplayDto(
        Long id,
        @NotNull @NotEmpty String firstName,
        @NotNull @NotEmpty String lastName,
        @NotNull @NotEmpty String profilePicUrl) implements Serializable {
}