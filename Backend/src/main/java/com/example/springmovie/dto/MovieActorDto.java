package com.example.springmovie.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class MovieActorDto {

    @NotNull
    @NotBlank
    private final Long movieId;

    @NotNull
    @NotBlank
    private final Long actorId;

    @NotNull
    @NotBlank
    private final String role;

    @Min(0)
    private final Integer displayOrder;

    private final String characterImageUrl;
}


