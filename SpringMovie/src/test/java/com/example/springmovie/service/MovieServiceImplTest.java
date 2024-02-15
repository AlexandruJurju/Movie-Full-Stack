package com.example.springmovie.service;

import com.example.springmovie.exception.NotFoundException;
import com.example.springmovie.model.Movie;
import com.example.springmovie.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @Test
    public void testFindAllMovies_HappyPath() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> expectedPage = new PageImpl<>(Collections.singletonList(new Movie()), pageable, 1);
        when(movieRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Movie> actualPage = movieService.findAllMovies(pageable);

        assertThat(actualPage, is(equalTo(expectedPage)));
        verify(movieRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testFindMovieById_ExistingMovie() {
        Long id = 1L;
        Movie expectedMovie = new Movie();
        expectedMovie.setId(id);
        when(movieRepository.findById(id)).thenReturn(Optional.of(expectedMovie));

        Movie actualMovie = movieService.findMovieById(id);

        assertThat(actualMovie, is(equalTo(expectedMovie)));
        verify(movieRepository, times(1)).findById(id);
    }

    @Test
    public void testFindMovieById_WhenMovieDoenstExist_ThrowNullPointerException() {
        Long movieId = 2L;
        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.findMovieById(movieId));
    }

    @Test
    void testUpdateMovie_WhenMovieExists() {
        Movie movie = new Movie();
        movie.setId(1L);
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie updatedMovie = movieService.updateMovie(movie);

        assertEquals(movie, updatedMovie);
    }

    @Test
    void testUpdateMovie_WhenMovieDoesNotExist() {
        Movie movie = new Movie();
        movie.setId(2L);
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.updateMovie(movie));
    }

    @Test
    void testDeleteMovieById_WhenMovieExists() {
        Long movieId = 1L;
        when(movieRepository.existsById(movieId)).thenReturn(true);

        movieService.deleteMovieById(movieId);

        verify(movieRepository, times(1)).deleteById(movieId);
    }

    @Test
    void testDeleteMovieById_WhenMovieDoesNotExist() {
        Long movieId = 2L;
        when(movieRepository.existsById(movieId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> movieService.deleteMovieById(movieId));
    }

}