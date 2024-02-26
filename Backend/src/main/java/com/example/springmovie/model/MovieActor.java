package com.example.springmovie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_actor")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;

    @Column(name = "role")
    private String role;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "character_image_url")
    private String characterImageUrl;
}
