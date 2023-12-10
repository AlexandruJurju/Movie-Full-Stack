package com.example.backend.model;

import com.example.backend.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "movie")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Schema(name = "Movie", description = "Schema to hold Movie information")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Movie title cannot be empty")
    @Column(name = "title")
    @Schema(description = "Title of the movie")
    private String title;

    @Column(name = "tagline")
    @Schema(description = "Punch-line of the movie")
    private String tagline;

    @Column(name = "overview", columnDefinition = "TEXT")
    @Schema(description = "Small description of the movie")
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

    @Column(name = "poster_url")
    private String posterURL;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd") // default html date type input date pattern "yyyy-mm-dd"
    private Date releaseDate;

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
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
    }

}