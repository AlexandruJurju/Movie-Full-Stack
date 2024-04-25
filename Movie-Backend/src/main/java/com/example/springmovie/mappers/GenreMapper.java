package com.example.springmovie.mappers;

import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.model.Genre;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {
    Genre toEntity(GenreDto genreDto);

    GenreDto toDto(Genre genre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Genre partialUpdate(GenreDto genreDto, @MappingTarget Genre genre);

    List<GenreDto> toDto(List<Genre> genre);

    Set<GenreDto> toDto(Set<Genre> genre);
}