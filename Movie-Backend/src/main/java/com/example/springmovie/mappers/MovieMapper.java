package com.example.springmovie.mappers;

import com.example.springmovie.dto.DetailedMovieDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.model.Movie;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    Movie toEntity(MovieDto movieDto);

    MovieDto toDto(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movie partialUpdate(MovieDto movieDto, @MappingTarget Movie movie);

    List<MovieDto> toDto(List<Movie> movie);
}