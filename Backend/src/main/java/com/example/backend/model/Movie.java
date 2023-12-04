package com.example.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "movie")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Schema(
        name = "Movie",
        description = "Schema to hold Movie information"
)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Movie title cannot be empty")
    @Column(name = "title")
    private String title;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "overview", columnDefinition = "TEXT") // Use appropriate database-specific type or length
    private String overview;

    @Column(name = "runtime")
    @Schema(description = "Runtime of the movie in minutes")
    private Integer runtime;

    @Column(name = "revenue")
    @Schema(description = "Revenue of the movie in millions of dollars")
    private Integer revenue;

    @Column(name = "budget")
    @Schema(description = "Budget of the movie in millions of dollars")
    private Integer budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "release_status")
    private ReleaseStatus releaseStatus;

    @Column(name = "votes")
    private Integer votes;

    @Column(name = "votes_average")
    @Schema(description = "Average score received by the movies, stored as float", example = "7.8")
    private Double votes_average;

    @OneToOne
    private Image image;

    // owning side of the many-to-many relationship
    // the owning side is responsible for updating the table
    // often add genres to movie, not movies to genres
    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new LinkedHashSet<>();

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getMovies().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getMovies().remove(this);
    }

}