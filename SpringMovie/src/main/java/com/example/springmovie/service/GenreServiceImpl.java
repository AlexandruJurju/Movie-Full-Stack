package com.example.springmovie.service;

import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.repositories.GenreRepository;
import com.example.springmovie.service.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findGenreById(Long id) throws GenreNotFoundException {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + id + " not found"));
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) throws GenreNotFoundException {
        genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + id + " not found"));
        genreRepository.deleteById(id);
    }
}
