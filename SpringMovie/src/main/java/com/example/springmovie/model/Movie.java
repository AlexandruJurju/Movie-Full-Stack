package com.example.springmovie.model;

import com.example.springmovie.enums.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
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
    private Integer runtime;

    @Enumerated(EnumType.STRING)
    @Column(name = "release_status")
    private ReleaseStatus releaseStatus;

    @Column(name = "poster_url")
    private String posterURL;

    @Column(name = "imdb_url")
    private String imdbUrl;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd") // default html date type input date pattern "yyyy-mm-dd"
    private Date releaseDate;

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
}