package com.example.springmovie.dto;

import jakarta.validation.constraints.NotNull;

public record MovieActorDto(
        @NotNull Long movieId,
        @NotNull Long actorId,
        String role,
        Integer displayOrder,
        String characterImageUrl) {
}

