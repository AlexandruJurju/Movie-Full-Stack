package com.example.backend.services.implementation;

import com.example.backend.model.Genre;
import com.example.backend.repositories.GenreRepository;
import com.example.backend.services.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find genre with id " + id));
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
