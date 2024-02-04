package com.example.springmovie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cast")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "listing_order")
    private int listingOrder;
}
