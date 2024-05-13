package com.example.springmovie.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springmovie.model.User}
 */
public record UserDto(
        @NotNull Long id,
        String username,
        String profilePictureUrl) implements Serializable {
}