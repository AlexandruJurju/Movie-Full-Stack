package com.example.springmovie.model;

import com.example.springmovie.enums.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// TODO: add keywords
// TODO: add more than 1 poster
// TODO: add more media
// TODO: add popularity
// TODO: add watched movies
// TODO: add watchlist
@Schema(name = "Movie", description = "Schema to hold Movie information")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    @Schema(description = "Title of the movie", example = "Alien")
    private String title;

    @Column(name = "tagline")
    @Schema(description = "Punch-line of the movie", example = "In space no one can hear you scream")
    private String tagline;

    @Column(name = "overview", columnDefinition = "TEXT")
    @Schema(description = "Small description of the movie")
    private String overview;

    @Column(name = "runtime")
    @Schema(description = "Runtime of the movie in minutes", example = "117")
    private Integer runtimeInMinutes;

    // TODO: remove, use releaseDate instead for search
    @Enumerated(EnumType.STRING)
    @Column(name = "release_status")
    private ReleaseStatus releaseStatus;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "imdb_url")
    private String imdbUrl;

    @Column(name = "imdb_rating")
    private Double imdbRating;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    // owning side of the many-to-many relationship - the owning side is responsible for updating the table
    // often add genres to movie, not movies to genres
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private Set<MovieActor> movieActors = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews = new HashSet<>();
}