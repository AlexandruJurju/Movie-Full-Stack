package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVotes_average() {
        return votes_average;
    }

    public void setVotes_average(Double votes_average) {
        this.votes_average = votes_average;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Movie() {

    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}