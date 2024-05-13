package com.example.springmovie.model;

import com.example.springmovie.enums.ReleaseStatus;
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
@Schema(name = "Movie", description = "Schema to hold Movie information")


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    @Schema(description = "Title of the movie", example = "Alien")
    private String title;

    @Column(name = "headline")
    @Schema(description = "Punch-line of the movie", example = "In space no one can hear you scream")
    private String headline;

    @Column(name = "overview", columnDefinition = "TEXT")
    @Schema(description = "Small description of the movie")
    private String overview;

    @Column(name = "runtime")
    @Schema(description = "Runtime of` the movie in minutes", example = "117")
    private Integer runtimeInMinutes;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "release_status")
    private ReleaseStatus releaseStatus;

    // owning side of the many-to-many relationship - the owning side is responsible for updating the table
    // often add genres to movie, not movies to genres
    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieActor> movieActors = new HashSet<>();
}