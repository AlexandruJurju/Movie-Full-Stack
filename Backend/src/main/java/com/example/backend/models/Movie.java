package com.example.backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "overview")
    private String overview;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "budget")
    private Integer budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "release_status")
    private ReleaseStatus releaseStatus;

    @Column(name = "votes")
    private Integer votes;

    @Column(name = "votes_average")
    private Double votes_average;

    // owning side of the many-to-many relationship
    // the owning side is responsible for updating the table
    // often add genres to movie, not movies to genres
    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new LinkedHashSet<>();

}