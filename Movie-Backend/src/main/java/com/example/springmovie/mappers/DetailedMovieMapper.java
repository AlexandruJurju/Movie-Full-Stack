package com.example.springmovie.mappers;

import com.example.springmovie.dto.DetailedMovieDto;
import com.example.springmovie.model.Movie;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DetailedMovieMapper {
    Movie toEntity(DetailedMovieDto detailedMovieDto);

    @AfterMapping
    default void linkMovieActors(@MappingTarget Movie movie) {
        movie.getMovieActors().forEach(movieActor -> movieActor.setMovie(movie));
    }

    DetailedMovieDto toDto(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movie partialUpdate(DetailedMovieDto detailedMovieDto, @MappingTarget Movie movie);
}