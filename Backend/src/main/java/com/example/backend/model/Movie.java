package com.example.backend.model;

import com.example.backend.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
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
    @Schema(example = "Alien")
    private String title;

    @Column(name = "tagline")
    @Schema(example = "In space no one can hear you scream.")
    private String tagline;

    @Column(name = "overview", columnDefinition = "TEXT") // Use appropriate database-specific type or length
    @Schema(example = "During its return to the earth, commercial spaceship Nostromo intercepts a distress signal from a distant planet. When a three-member team of the crew discovers a chamber containing thousands of eggs on the planet, a creature inside one of the eggs attacks an explorer. The entire crew is unaware of the impending nightmare set to descend upon them when the alien parasite planted inside its unfortunate host is birthed.")
    private String overview;

    @Column(name = "runtime")
    @Schema(description = "Runtime of the movie in minutes", example = "117")
    private Integer runtime;

    @Column(name = "revenue")
    @Schema(description = "Revenue of the movie in millions of dollars", example = "104")
    private Integer revenue;

    @Column(name = "budget")
    @Schema(description = "Budget of the movie in millions of dollars", example = "11")
    private Integer budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "release_status")
    private ReleaseStatus releaseStatus;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "poster_url")
    private String posterURL;

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