package com.example.springmovie.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record MovieActorDto(
        @NotNull @NotBlank Long movieId,
        @NotNull @NotBlank Long actorId,
        @NotNull @NotBlank String role,
        @Min(0) Integer displayOrder,
        String characterImageUrl
) {

}


