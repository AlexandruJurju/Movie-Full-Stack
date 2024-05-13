package com.example.springmovie.service;

import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.mappers.GenreMapper;
import com.example.springmovie.mappers.MovieMapper;
import com.example.springmovie.model.Genre;
import com.example.springmovie.repositories.GenreRepository;
import com.example.springmovie.service.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;


    private final GenreMapper genreMapper;
    private final MovieMapper movieMapper;

    @Override
    public List<GenreDto> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toDto(genres);
    }

    @Override
    public Optional<GenreDto> findGenreById(Long id) {
        return genreRepository.findById(id).map(genreMapper::toDto);
    }

    @Override
    public GenreDto saveGenre(GenreDto genreDto) {
        Genre genre = genreMapper.toEntity(genreDto);
        Genre genreSaved = genreRepository.save(genre);
        return genreMapper.toDto(genreSaved);
    }

    @Override
    public GenreDto updateGenre(Long genreId, GenreDto genreDto) throws GenreNotFoundException {

        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (genreOptional.isEmpty()) throw new GenreNotFoundException("Genre with it " + genreId + " not found");

        Genre genre = genreOptional.get();
        genre.setName(genreDto.name());
        return genreMapper.toDto(genreRepository.save(genre));
    }

    @Override
    public void deleteGenre(Long id) throws GenreNotFoundException {
        genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + id + " not found"));
        genreRepository.deleteById(id);
    }

    @Override
    public List<MovieDto> findAllMoviesWithGenre(Long genreId) {
        return movieMapper.toDto(genreRepository.findMoviesByGenreId(genreId));
    }
}
